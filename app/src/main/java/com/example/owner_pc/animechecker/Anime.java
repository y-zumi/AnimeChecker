package com.example.owner_pc.animechecker;

/**
 * Created by owner-PC on 2017/05/12.
 */

public class Anime {
    private int id;
    private String title;
    public Anime(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
