package com.example.owner_pc.animechecker.model.entity;


import java.io.Serializable;

/**
 * Created by owner-PC on 2017/05/21.
 */

public class Staff implements Serializable {
    public int id;
    public String nameFirst;
    public String nameLast;
    public String nameFirstJapanese;
    public String nameLastJapanese;
    public String info;
    public String language;
    public String imageUrlLge;
    public String imageUrlMed;

    @Override
    public String toString() {
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
                '}';
    }

    public Staff() {
        this.id = -1;
        this.nameFirst = "";
        this.nameLast = "";
        this.nameFirstJapanese = "";
        this.nameLastJapanese = "";
        this.info = "";
        this.language = "";
        this.imageUrlLge = "";
        this.imageUrlMed = "";
    }
}
