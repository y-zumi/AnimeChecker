package com.example.owner_pc.animechecker.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.example.owner_pc.animechecker.contract.AnimeDetailContract;
import com.example.owner_pc.animechecker.model.AniListService;
import com.example.owner_pc.animechecker.model.entity.AnimePage;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
//    public ObservableField<String> officialSiteUrl = new ObservableField<>();
//    public ObservableField<String> twitterUrl = new ObservableField<>();
    public ObservableField<List<String>> castList = new ObservableField<>();
    public ObservableField<List<String>> staffList = new ObservableField<>();
    private AnimePage animePage;

    public AnimeDetailViewModel(AnimeDetailContract detailView, AniListService aniListService) {
        this.detailView = detailView;
        this.aniListService = aniListService;
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
