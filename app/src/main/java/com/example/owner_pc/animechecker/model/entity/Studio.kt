package com.example.owner_pc.animechecker.model.entity

import java.io.Serializable

/**
 * Created by owner-PC on 2017/05/21.
 */

open class Studio : Serializable {
    var id: Int = 0
    var studioName: String? = null
    var studioWiki: String? = null
    //    public ArrayList<Anime> anime;

    override fun toString(): String {
        return "Studio{" +
                "id=" + id +
                ", studioName='" + studioName + '\'' +
                ", studioWiki='" + studioWiki + '\'' +
                //                ", anime=" + anime +
                '}'
    }
}
