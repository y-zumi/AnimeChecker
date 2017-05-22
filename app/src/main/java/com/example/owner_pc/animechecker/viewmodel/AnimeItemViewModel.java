package com.example.owner_pc.animechecker.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.view.View;

import com.example.owner_pc.animechecker.contract.AnimeListViewContract;
import com.example.owner_pc.animechecker.model.AniListService;
import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;
import com.example.owner_pc.animechecker.model.entity.Staff;
import com.example.owner_pc.animechecker.model.entity.StaffSmall;
import com.example.owner_pc.animechecker.model.entity.Studio;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by owner-PC on 2017/05/22.
 */

public class AnimeItemViewModel {
    public ObservableField<String> animeName = new ObservableField<>();
    public ObservableField<String> animeImageUrl = new ObservableField<>();
    public ObservableField<String> directorName = new ObservableField<>();
    public ObservableField<String> studioName = new ObservableField<>();
    AnimeListViewContract view;
    private AnimePage animePage;

    public AnimeItemViewModel(AnimeListViewContract view) {
        this.view = view;
    }

    public void loadItem(AnimeCard item) {
        animePage = item.animePage;
        animeName.set(item.animePage.titleJapanese);
        animeImageUrl.set(item.animePage.imageUrlLge);
        studioName.set(item.animePage.studio.get(0).studioName);
        directorName.set(item.director.nameLastJapanese + swapName(item.director.nameFirstJapanese));
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
        // todo 監督と制作会社取得の際に、フェッチしてきたAnimePageを渡す
        view.startDetailActivity(animePage);
    }
}
