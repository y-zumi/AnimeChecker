package com.example.owner_pc.animechecker.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.example.owner_pc.animechecker.contract.AnimeListFragmentContract;
import com.example.owner_pc.animechecker.contract.AnimeListViewContract;
import com.example.owner_pc.animechecker.contract.AnimeSmallListFragmentContract;
import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;

/**
 * Created by owner-PC on 2017/05/22.
 */

public class AnimeItemViewModel {
    public ObservableField<String> animeName = new ObservableField<>();
    public ObservableField<String> animeImageUrl = new ObservableField<>();
    public ObservableField<String> directorName = new ObservableField<>();
    public ObservableField<String> studioName = new ObservableField<>();
    public ObservableField<String> season = new ObservableField<>();
    AnimeListViewContract view;
    AnimeListFragmentContract fragmentView;
    AnimeSmallListFragmentContract fragmentSmallView;
    private AnimePage animePage;
    private Anime anime;

    public AnimeItemViewModel(AnimeListViewContract view) {
        this.view = view;
    }

    public AnimeItemViewModel(AnimeListFragmentContract view) {
        this.fragmentView = view;
    }

    public AnimeItemViewModel(AnimeSmallListFragmentContract view) {
        this.fragmentSmallView = view;
    }

    public void loadItem(AnimeCard item) {
        animePage = item.animePage;
        animeName.set(item.animePage.titleJapanese);
        animeImageUrl.set(item.animePage.imageUrlLge);
        studioName.set(item.animePage.studio.get(0).studioName);
        directorName.set(item.director.nameLastJapanese + swapName(item.director.nameFirstJapanese));
        season.set(item.animePage.getSeason());
    }

    public void loadSmallItem(Anime item) {
//        animePage = item.animePage;
        anime = item;
        animeName.set(item.titleJapanese);
        animeImageUrl.set(item.imageUrlLge);
//        studioName.set(item.animePage.studio.get(0).studioName);
//        directorName.set(item.director.nameLastJapanese + swapName(item.director.nameFirstJapanese));
        season.set(item.getSeason());
    }


    // apiの文字列表示がおかしいため、少し修正を行った
    private String swapName(String str) {
        String[] name = str.split(" ");
        if (name.length == 2) {
            return name[1] + " " + name[0];
        }
        if (name.length > 2) {
            return name[2] + " " + name[1] + name[0];
        }
        return str;
    }

    public void onItemClick(View itemView) {
        if (view != null) view.startDetailActivity(animePage);
        if (fragmentView != null) fragmentView.itemClicked(animePage);
        if (fragmentSmallView != null) fragmentSmallView.itemClicked(anime);
    }
}
