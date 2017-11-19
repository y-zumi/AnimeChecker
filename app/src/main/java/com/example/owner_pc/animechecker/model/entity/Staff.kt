package com.example.owner_pc.animechecker.model.entity


import java.io.Serializable

/**
 * Created by owner-PC on 2017/05/21.
 */

open class Staff : Serializable {
    var id: Int = 0
    var nameFirst: String
    var nameLast: String
    var nameFirstJapanese: String
    var nameLastJapanese: String
    var info: String
    var language: String
    var imageUrlLge: String
    var imageUrlMed: String

    init {
        this.id = -1
        this.nameFirst = ""
        this.nameLast = ""
        this.nameFirstJapanese = ""
        this.nameLastJapanese = ""
        this.info = ""
        this.language = ""
        this.imageUrlLge = ""
        this.imageUrlMed = ""
    }

    override fun toString(): String {
        return "Staff{" +
                "id=" + id +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", nameFirstJapanese='" + nameFirstJapanese + '\'' +
                ", nameLastJapanese='" + nameLastJapanese + '\'' +
                ", info='" + info + '\'' +
                ", language='" + language + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                '}'
    }
}
