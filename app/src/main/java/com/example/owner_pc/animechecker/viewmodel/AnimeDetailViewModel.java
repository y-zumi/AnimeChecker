package com.example.owner_pc.animechecker.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.example.owner_pc.animechecker.contract.AnimeDetailContract;
import com.example.owner_pc.animechecker.model.AniListService;
import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;
import com.example.owner_pc.animechecker.model.entity.StaffPage;
import com.example.owner_pc.animechecker.model.entity.StudioPage;

import java.util.ArrayList;
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

public class AnimeDetailViewModel {

    final AnimeDetailContract detailView;
    private final AniListService aniListService;
    public ObservableField<String> animeBannerUrl = new ObservableField<>();
    public ObservableField<String> animeIconUrl = new ObservableField<>();
    public ObservableField<String> animeTitle = new ObservableField<>();
    public ObservableField<String> animeSeason = new ObservableField<>();
    public ObservableField<String> animeDirector = new ObservableField<>();
    public ObservableField<String> animeStudio = new ObservableField<>();
//    public ObservableField<String> officialSiteUrl = new ObservableField<>();
//    public ObservableField<String> twitterUrl = new ObservableField<>();
    public ObservableField<List<String>> castList = new ObservableField<>();
    public ObservableField<List<String>> staffList = new ObservableField<>();
    private AnimePage animePage;
    private final SharedPreferences preferences;

    public AnimeDetailViewModel(AnimeDetailContract detailView, AniListService aniListService, Context context) {
        this.detailView = detailView;
        this.aniListService = aniListService;
        this.preferences = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);

    }

    public void prepare() {
        loadAnimeDetail();
    }

    /**
     * 一つのリポジトリについての情報を取得する
     * 基本的にAPIアクセス方法についてはRepositoryListActivity#loadRepositories(String)と同じ
     */
    public void loadAnimeDetail() {
        AnimePage item = detailView.getAnimePage();
        setAnimeData(item);

        // Retrofitを利用してサーバーにアクセスする
        // 監督の関連アニメを取得
        Observable<StaffPage> observableStaff = aniListService.listDirectorAnimes(item.getDirector().id,  preferences.getString("token", ""));
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observableStaff.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StaffPage>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull StaffPage item) {
                // 読み込み終了したので、プログレスバーの表示を非表示にする
//                progressBarVisibility.set(View.GONE);
                // 取得したアイテムを表示するために、RecyclerViewにアイテムをセットして更新する
//                animeListView.showAnimes(animes);
                // 今期のアニメリストを格納する(監督情報を格納したアニメリストとの数を一致させるため)
//                animeList = items;
                //// TODO: 2017/05/29 List<Anime>のまま渡せるようにする
//                List<AnimeCard> animes = new ArrayList<AnimeCard>();
//                // 各アニメの詳細情報を取得
//                for (int i = 0; i < item.animeStaff.size(); i++) {
//                    animes.add(new AnimeCard(item.animeStaff.get(i)));
//                }
                detailView.showDirectorAnimes(item.animeStaff);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                detailView.showError("監督の関連作品を読み込めませんでした");
            }

            @Override
            public void onComplete() {
            }
        });

        Observable<StudioPage> observableStudio = aniListService.listStudioAnimes(item.studio.get(0).id,  preferences.getString("token", ""));
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observableStudio.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StudioPage>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull StudioPage item) {
                // 読み込み終了したので、プログレスバーの表示を非表示にする
//                progressBarVisibility.set(View.GONE);
                // 取得したアイテムを表示するために、RecyclerViewにアイテムをセットして更新する
//                animeListView.showAnimes(animes);
                // 今期のアニメリストを格納する(監督情報を格納したアニメリストとの数を一致させるため)
//                animeList = items;
                // 各アニメの詳細情報を取得
//                for (int i = 0; i < items.size(); i++) {
//                    loadAnimePage(items.get(i).id);
//                }
                //// TODO: 2017/05/29 List<Anime>のまま渡せるようにする
//                List<AnimeCard> animes = new ArrayList<AnimeCard>();
//                // 各アニメの詳細情報を取得
//                for (int i = 0; i < item.anime.size(); i++) {
//                    animes.add(new AnimeCard(item.anime.get(i)));
//                }
                detailView.showStudioAnimes(item.anime);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                detailView.showError("制作会社の関連作品を読み込めませんでした");
            }

            @Override
            public void onComplete() {
            }
        });

        // リポジトリの名前を/で分割する
//        final String[] repoData = fullRepoName.split("/");
//        final String owner = repoData[0];
//        final String repoName = repoData[1];
//        aniListService
//                .detailRepo(owner, repoName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<AniListService.RepositoryItem>() {
//                    @Override
//                    public void onCompleted() {
//                        // 何もしない
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        detailView.showError("読み込めませんでした。");
//                    }
//
//                    @Override
//                    public void onNext(AnimePage animePage) {
//                        setAnimeData(animePage);
//                    }
//                });
    }

    // データをbind
    private void setAnimeData(AnimePage item) {
        this.animePage = item;
        animeBannerUrl.set(item.imageUrlBanner);
        animeIconUrl.set(item.imageUrlMed);
        animeTitle.set(item.titleJapanese);
        animeSeason.set(item.getSeason());
        if (item.getDirector() != null) animeDirector.set(item.getDirector().nameLast + item.getDirector().nameFirst);
        else animeDirector.set("");
        animeStudio.set(item.studio.get(0).studioName);
//        officialSiteUrl.set(item.getOfficialSite());
//        twitterUrl.set(item.getTwitterUrl());
        castList.set(item.getCasts());
        staffList.set(item.getStaffs());
    }


    public void onOfficialSiteClick(View v) {
        try {
            detailView.startBrowser(animePage.getOfficialSite());
        } catch (Exception e) {
            detailView.showError("リンクを開けませんでした。");
        }
    }

    public void onTwitterClick(View v) {
        try {
            detailView.startBrowser(animePage.getTwitterUrl());
        } catch (Exception e) {
            detailView.showError("リンクを開けませんでした。");
        }
    }
}
