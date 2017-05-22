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

//    public void onLanguageSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //  スピナーの選択内容が変わったら呼ばれる
//        loadRepositories((String) parent.getItemAtPosition(position));
//    }

    public void fetchToken() {
        // 過去一週間で作られて、言語がlanguageのものをクエリとして渡す
        Observable<Token> observable = aniListService.requestToken("client_credentials","y0zumi-ajwul","KpyrM3RTS0RAt0QIs1AmCR9Q4");
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Token>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Token token) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token", token.accessToken);
                editor.commit();
                // 読み込み終了したので、プログレスバーの表示を非表示にする
//                progressBarVisibility.set(View.GONE);
                // 取得したアイテムを表示するために、RecyclerViewにアイテムをセットして更新する
//                animeListView.showAnimes(token);
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

        // 一週間前の日付の文字列 今が2016-10-27ならば2016-10-20 などの文字列を取得する
//        final Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -7);
//        String text = DateFormat.format("yyyy-MM-dd", calendar).toString();

        // Retrofitを利用してサーバーにアクセスする
        // 2017年春アニメ(TV版)を取得
        Observable<List<Anime>> observable = aniListService.listAnimes("2017", "spring", "TV", preferences.getString("token", ""));
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Anime>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Anime> items) {
                // 読み込み終了したので、プログレスバーの表示を非表示にする
//                progressBarVisibility.set(View.GONE);
                // 取得したアイテムを表示するために、RecyclerViewにアイテムをセットして更新する
//                animeListView.showAnimes(animes);
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
        Observable<AnimePage> observable = aniListService.detailAnime(animeId,  preferences.getString("token", ""));
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AnimePage>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull AnimePage item) {
//                animePage = item;
//                studioName.set(animePage.studio.get(0).studioName);
//                loadDirector(animePage.getDirector());
                // 詳細情報から監督のidを取得し、監督の名前(日本語)を取ってくる
                loadDirector(item.getDirector(), item);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    // 監督情報(日本語の名前)を取得
    public void loadDirector(StaffSmall staff, final AnimePage animePage) {
        // 監督情報がない場合アニメの情報のみ格納
        if (staff == null) {
            animeCards.add(new AnimeCard(animePage));
            checkLoadedAnimeList();
            return;
        }
        Observable<Staff> observable = aniListService.detailStaff(staff.id,  preferences.getString("token", ""));
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
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

