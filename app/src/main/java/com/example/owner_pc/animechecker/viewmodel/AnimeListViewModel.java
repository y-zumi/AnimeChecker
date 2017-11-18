package com.example.owner_pc.animechecker.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableInt;
import android.view.View;

import com.example.owner_pc.animechecker.contract.AnimeListViewContract;
import com.example.owner_pc.animechecker.model.AniListService;
import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;
import com.example.owner_pc.animechecker.model.entity.Staff;
import com.example.owner_pc.animechecker.model.entity.StaffSmall;
import com.example.owner_pc.animechecker.model.entity.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by owner-PC on 2017/05/22.
 */

public class AnimeListViewModel {
    public final ObservableInt progressBarVisibility = new ObservableInt(View.VISIBLE);
    private final AnimeListViewContract animeListView;
    private final AniListService aniListService;
    private final Context context;
    private final SharedPreferences preferences;
    private List<Anime> animeList = new ArrayList<>();      // アニメ一覧(監督情報なし)
    private List<AnimeCard> animeCards = new ArrayList<>(); // アニメ一覧(監督情報あり)

    public AnimeListViewModel(AnimeListViewContract animeListView, AniListService aniListService, Context context) {
        this.animeListView = animeListView;
        this.aniListService = aniListService;
        this.context = context;
        this.preferences = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);
    }

    // アクセストークン取得
    public void fetchToken() {
        Observable<Token> observable = aniListService.requestToken("client_credentials", "y0zumi-ajwul", "KpyrM3RTS0RAt0QIs1AmCR9Q4");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Token>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Token token) {
                // トークンを保存
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token", token.accessToken);
                editor.commit();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                animeListView.showError();
            }

            // token取得後アニメリスト取得
            @Override
            public void onComplete() {
                loadAnimes();
            }
        });
    }

    /**
     * 過去一週間で作られたライブラリのスター数順で取得
     */
    public void loadAnimes() {
        // 読込中なのでプログレスバーを表示する
        progressBarVisibility.set(View.VISIBLE);

        // Retrofitを利用してサーバーにアクセスする
        // 2017年春アニメ(TV版)を取得
        Observable<List<Anime>> observable = aniListService.listAnimes("2017", "summer", "TV", preferences.getString("token", ""));
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Anime>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Anime> items) {
                // 今期のアニメリストを格納する(監督情報を格納したアニメリストとの数を一致させるため)
                animeList = items;
                // 各アニメの詳細情報を取得
                for (int i = 0; i < items.size(); i++) {
                    loadAnimePage(items.get(i).id);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                animeListView.showError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 監督と制作会社の名称は別途通信が必要である
    public void loadAnimePage(int animeId) {
        Observable<AnimePage> observable = aniListService.detailAnime(animeId, preferences.getString("token", ""));
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AnimePage>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull AnimePage item) {
                // 詳細情報から監督のidを取得し、監督の名前(日本語)を取ってくる
                loadDirector(item.getDirector(), item);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                animeListView.showError();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    // 監督情報(日本語の名前)を取得
    public void loadDirector(StaffSmall staff, final AnimePage animePage) {
        // 監督情報がない場合アニメの情報のみ格納
        if (staff == null || staff.id == -1) {
            animeCards.add(new AnimeCard(animePage));
            checkLoadedAnimeList();
            return;
        }
        Observable<Staff> observable = aniListService.detailStaff(staff.id, preferences.getString("token", ""));
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Staff>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Staff item) {
                // 監督情報とアニメ情報を格納
                animeCards.add(new AnimeCard(animePage, item));
                checkLoadedAnimeList();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                animeListView.showError();
            }

            @Override
            public void onComplete() {

            }
        });


    }

    // アニメの詳細情報がすべて取得できたか確認した後、リストを表示
    private void checkLoadedAnimeList() {
        if (animeList.size() == animeCards.size()) {
            // 読み込み終了したので、プログレスバーの表示を非表示にする
            progressBarVisibility.set(View.GONE);
            Collections.sort(animeCards, new Comparator<AnimeCard>() {
                @Override
                public int compare(AnimeCard o1, AnimeCard o2) {
                    return o1.animePage.popularity > o2.animePage.popularity ? -1 : 1;
                }
            });
            animeListView.showAnimes(animeCards);
        }
    }

}

