package com.example.owner_pc.animechecker;

import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by owner-PC on 2017/05/22.
 */

public class AnimeItemViewModel {
    public ObservableField<String> animeName = new ObservableField<>();
//    public ObservableField<String> repoDetail = new ObservableField<>();
//    public ObservableField<String> repoStar = new ObservableField<>();
    public ObservableField<String> animeImageUrl = new ObservableField<>();

    AnimeListViewContract view;
    private int animeId;

    public AnimeItemViewModel(AnimeListViewContract view) {
        this.view = view;
    }

    public void loadItem(Anime item) {
        animeId = item.id;
//        repoDetail.set(item.description);
        animeName.set(item.titleJapanese);
//        repoStar.set(item.stargazers_count);
        animeImageUrl.set(item.imageUrlMed);
    }

    public void onItemClick(View itemView) {
        view.startDetailActivity(animeId);
    }
}
