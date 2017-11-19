package com.example.owner_pc.animechecker.contract

import com.example.owner_pc.animechecker.model.entity.AnimeCard
import com.example.owner_pc.animechecker.model.entity.AnimePage

/**
 * Created by owner-PC on 2017/05/22.
 */

interface AnimeListViewContract {
    fun showAnimes(animes: List<AnimeCard>)

    fun showError()

    fun startDetailActivity(animePage: AnimePage)
}
