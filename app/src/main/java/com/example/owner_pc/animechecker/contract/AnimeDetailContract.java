package com.example.owner_pc.animechecker.contract;

import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimePage;

import java.util.List;

/**
 * Created by owner-PC on 2017/05/22.
 */

public interface AnimeDetailContract {

    AnimePage getAnimePage();

    void startBrowser(String url);

    //// TODO: 2017/05/29 AnimeCardではなくAnimeで渡した場合に変更する
    void showDirectorAnimes(List<Anime> animes);

    void showStudioAnimes(List<Anime> animes);

    void showError(String message);
}
