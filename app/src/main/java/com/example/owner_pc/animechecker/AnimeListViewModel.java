package com.example.owner_pc.animechecker;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableInt;
import android.view.View;

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
            public void onNext(@NonNull List<Anime> animes) {
                // 読み込み終了したので、プログレスバーの表示を非表示にする
                progressBarVisibility.set(View.GONE);
                // 取得したアイテムを表示するために、RecyclerViewにアイテムをセットして更新する
                animeListView.showAnimes(animes);
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

}

