package com.example.owner_pc.animechecker.model.entity

import java.io.Serializable

/**
 * Created by owner-PC on 2017/05/21.
 */

class ExternalLinks : Serializable {
    var id: Int = 0
    var url: String? = null
    var site: String? = null

    override fun toString(): String {
        return "ExternalLinks{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", site='" + site + '\'' +
                '}'
    }
}
