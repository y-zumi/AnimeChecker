package com.example.owner_pc.animechecker;

import java.util.List;

/**
 * Created by owner-PC on 2017/05/22.
 */

public interface AnimeListViewContract {
    void showAnimes(List<Anime> animes);

    void showError();

    void startDetailActivity(int animeId);
}
