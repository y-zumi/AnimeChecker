package com.example.owner_pc.animechecker.model.entity

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by owner-PC on 2017/05/20.
 */

class Characters : Serializable {
    var id: Int = 0
    var nameFirst: String? = null
    var nameLast: String? = null
    var imageUrlLge: String? = null
    var imageUrlMed: String? = null
    var role: String? = null
    var linkId: Int = 0
    var actor: ArrayList<StaffSmall>? = null

    override fun toString(): String {
        return "Characters{" +
                "id=" + id +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                ", role='" + role + '\'' +
                ", linkId=" + linkId +
                ", actor=" + actor +
                '}'
    }
}
