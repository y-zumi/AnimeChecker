package com.example.owner_pc.animechecker.model.entity;

import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/21.
 */

public class StudioPage extends Studio {
    public ArrayList<Anime> anime;

    @Override
    public String toString() {
        return "StudioPage{" +
                "anime=" + anime +
                '}';
    }
}
