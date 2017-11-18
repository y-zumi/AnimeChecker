package com.example.owner_pc.animechecker.model.entity;

import java.io.Serializable;

/**
 * Created by owner-PC on 2017/05/20.
 */

public class Character implements Serializable {
    public int id;
    public String nameFirst;
    public String nameLast;
    public String nameJapanese;
    public String nameAlt;
    public String info;
    public String imageUrlLge;
    public String imageUrlMed;
    public String role;

    public Character(String nameAlt, String info, int id, String nameFirst, String nameLast, String nameJapanese, String imageUrlLge, String imageUrlMed, String role) {
        this.nameAlt = nameAlt;
        this.info = info;
        this.id = id;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.nameJapanese = nameJapanese;
        this.imageUrlLge = imageUrlLge;
        this.imageUrlMed = imageUrlMed;
        this.role = role;
    }

    @Override
    public String toString() {
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
                '}';
    }
}
