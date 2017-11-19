package com.example.owner_pc.animechecker.model.entity

/**
 * Created by owner-PC on 2017/05/22.
 */

// listviewに表示するアニメ(カードの内容)
class AnimeCard {
    var animePage: AnimePage
    var director: Staff

    constructor(animePage: AnimePage, director: Staff) {
        this.animePage = animePage
        this.director = director
    }

    constructor(animePage: AnimePage) {
        this.animePage = animePage
        this.director = Staff()
    }
}
