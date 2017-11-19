package com.example.owner_pc.animechecker.contract

import com.example.owner_pc.animechecker.model.entity.Anime

/**
 * Created by owner-PC on 2017/05/29.
 */

interface AnimeSmallListFragmentContract {

    //    void showAnimes(List<AnimeCard> animes);

    fun showError()

    fun itemClicked(item: Anime)
}
