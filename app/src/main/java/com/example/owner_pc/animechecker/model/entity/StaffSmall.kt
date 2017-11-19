package com.example.owner_pc.animechecker.model.entity

import java.io.Serializable

/**
 * Created by owner-PC on 2017/05/20.
 */

class StaffSmall : Serializable {
    var id: Int = 0
    var nameFirst: String
    var nameLast: String
    var language: String
    var imageUrlLge: String
    var imageUrlMed: String
    var linkId: Int = 0
    var role: String

    //
    init {
        this.id = -1
        this.nameFirst = ""
        this.nameLast = ""
        this.language = ""
        this.imageUrlLge = ""
        this.imageUrlMed = ""
        this.linkId = -1
        this.role = ""
    }

    override fun toString(): String {
        return "StaffSmall{" +
                "id=" + id +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", language='" + language + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                ", linkId=" + linkId +
                ", role='" + role + '\'' +
                '}'
    }
}
