package com.example.owner_pc.animechecker.viewmodel

import android.databinding.ObservableField
import android.view.View

import com.example.owner_pc.animechecker.contract.AnimeListFragmentContract
import com.example.owner_pc.animechecker.contract.AnimeListViewContract
import com.example.owner_pc.animechecker.contract.AnimeSmallListFragmentContract
import com.example.owner_pc.animechecker.model.entity.Anime
import com.example.owner_pc.animechecker.model.entity.AnimeCard
import com.example.owner_pc.animechecker.model.entity.AnimePage

/**
 * Created by owner-PC on 2017/05/22.
 */

class AnimeItemViewModel {
    var animeName = ObservableField<String>()
    var animeImageUrl = ObservableField<String>()
    var directorName = ObservableField<String>()
    var studioName = ObservableField<String>()
    var season = ObservableField<String>()
    internal var view: AnimeListViewContract? = null
    internal var fragmentView: AnimeListFragmentContract? = null
    internal var fragmentSmallView: AnimeSmallListFragmentContract? = null
    private var animePage: AnimePage? = null
    private var anime: Anime? = null

    constructor(view: AnimeListViewContract) {
        this.view = view
    }

    constructor(view: AnimeListFragmentContract) {
        this.fragmentView = view
    }

    constructor(view: AnimeSmallListFragmentContract) {
        this.fragmentSmallView = view
    }

    fun loadItem(item: AnimeCard) {
        animePage = item.animePage
        animeName.set(item.animePage.titleJapanese)
        animeImageUrl.set(item.animePage.imageUrlLge)
        studioName.set(item.animePage.studio!![0].studioName)
        directorName.set(item.director.nameLastJapanese + swapName(item.director.nameFirstJapanese))
        season.set(item.animePage.getSeason())
    }

    fun loadSmallItem(item: Anime) {
        //        animePage = item.animePage;
        anime = item
        animeName.set(item.titleJapanese)
        animeImageUrl.set(item.imageUrlLge)
        //        studioName.set(item.animePage.studio.get(0).studioName);
        //        directorName.set(item.director.nameLastJapanese + swapName(item.director.nameFirstJapanese));
        season.set(item.getSeason())
    }


    // apiの文字列表示がおかしいため、少し修正を行った
    private fun swapName(str: String): String {
        val name = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (name.size == 2) {
            return name[1] + " " + name[0]
        }
        return if (name.size > 2) {
            name[2] + " " + name[1] + name[0]
        } else str
    }

    fun onItemClick(itemView: View) {
        if (view != null) view!!.startDetailActivity(animePage!!)
        if (fragmentView != null) fragmentView!!.itemClicked(animePage!!)
        if (fragmentSmallView != null) fragmentSmallView!!.itemClicked(anime!!)
    }
}
