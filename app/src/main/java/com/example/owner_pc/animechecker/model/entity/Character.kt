package com.example.owner_pc.animechecker.model.entity

import java.io.Serializable

/**
 * Created by owner-PC on 2017/05/20.
 */

class Character(var nameAlt: String, var info: String, var id: Int, var nameFirst: String, var nameLast: String, var nameJapanese: String, var imageUrlLge: String, var imageUrlMed: String, var role: String) : Serializable {

    override fun toString(): String {
        return "Character{" +
                "nameAlt='" + nameAlt + '\'' +
                ", info='" + info + '\'' +
                ", id=" + id +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", nameJapanese='" + nameJapanese + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                ", role='" + role + '\'' +
                '}'
    }
}
