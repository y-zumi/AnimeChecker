package com.example.owner_pc.animechecker.contract;

import com.example.owner_pc.animechecker.model.entity.AnimePage;

/**
 * Created by owner-PC on 2017/05/29.
 */

public interface AnimeListFragmentContract {

//    void showAnimes(List<AnimeCard> animes);

    void showError();

    void itemClicked(AnimePage animePage);
}
