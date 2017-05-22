package com.example.owner_pc.animechecker.contract;

import com.example.owner_pc.animechecker.model.entity.AnimePage;

/**
 * Created by owner-PC on 2017/05/22.
 */

public interface AnimeDetailContract {

    AnimePage getAnimePage();

    void startBrowser(String url);

    void showError(String message);
}
