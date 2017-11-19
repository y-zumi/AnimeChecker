package com.example.owner_pc.animechecker.contract

import com.example.owner_pc.animechecker.model.entity.Anime
import com.example.owner_pc.animechecker.model.entity.AnimePage

/**
 * Created by owner-PC on 2017/05/22.
 */

interface AnimeDetailContract {

    val animePage: AnimePage

    fun startBrowser(url: String)

    //// TODO: 2017/05/29 AnimeCardではなくAnimeで渡した場合に変更する
    fun showDirectorAnimes(animes: List<Anime>)

    fun showStudioAnimes(animes: List<Anime>)

    fun showError(message: String)
}
