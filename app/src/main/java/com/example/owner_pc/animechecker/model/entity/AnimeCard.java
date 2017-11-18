package com.example.owner_pc.animechecker.model.entity;

import java.util.List;

/**
 * Created by owner-PC on 2017/05/22.
 */

// listviewに表示するアニメ(カードの内容)
public class AnimeCard {
    public AnimePage animePage;
    public Staff director;

    public AnimeCard(AnimePage animePage, Staff director) {
        this.animePage = animePage;
        this.director = director;
    }
    public AnimeCard(AnimePage animePage) {
        this.animePage = animePage;
        this.director = new Staff();
    }
}
