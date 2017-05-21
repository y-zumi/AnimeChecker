package com.example.owner_pc.animechecker;

import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/21.
 */

public class Studio {
    public int id;
    public String studioName;
    public String studioWiki;
//    public ArrayList<Anime> anime;

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", studioName='" + studioName + '\'' +
                ", studioWiki='" + studioWiki + '\'' +
//                ", anime=" + anime +
                '}';
    }
}
