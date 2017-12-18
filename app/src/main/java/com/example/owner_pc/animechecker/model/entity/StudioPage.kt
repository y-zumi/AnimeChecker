package com.example.owner_pc.animechecker.model.entity

import java.util.*

/**
 * Created by owner-PC on 2017/05/21.
 */

class StudioPage : Studio() {
    var anime: ArrayList<Anime>? = null

    override fun toString(): String {
        return "StudioPage{" +
                "anime=" + anime +
                '}'
    }
}
