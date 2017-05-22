package com.example.owner_pc.animechecker.model.entity;

import java.io.Serializable;

/**
 * Created by owner-PC on 2017/05/20.
 */

public class StaffSmall implements Serializable {
    public int id;
    public String nameFirst;
    public String nameLast;
    public String language;
    public String imageUrlLge;
    public String imageUrlMed;
    public int linkId;
    public String role;

    @Override
    public String toString() {
        return "StaffSmall{" +
                "id=" + id +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", language='" + language + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                ", linkId=" + linkId +
                ", role='" + role + '\'' +
                '}';
    }
}
