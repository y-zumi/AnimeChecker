package com.example.owner_pc.animechecker;

import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/20.
 */

public class Characters {
    public int id;
    public String nameFirst;
    public String nameLast;
    public String imageUrlLge;
    public String imageUrlMed;
    public String role;
    public int linkId;
    public ArrayList<StaffSmall> actor;

    @Override
    public String toString() {
        return "Characters{" +
                "id=" + id +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                ", role='" + role + '\'' +
                ", linkId=" + linkId +
                ", actor=" + actor +
                '}';
    }
}
