package com.example.owner_pc.animechecker.contract;

import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;

import java.util.List;

/**
 * Created by owner-PC on 2017/05/29.
 */

public interface AnimeListFragmentContract {

//    void showAnimes(List<AnimeCard> animes);

    void showError();

    void itemClicked(AnimePage animePage);
}
