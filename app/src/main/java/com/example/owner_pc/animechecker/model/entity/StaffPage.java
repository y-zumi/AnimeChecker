package com.example.owner_pc.animechecker.model.entity;

import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/21.
 */

public class StaffPage extends Staff {
    public ArrayList<Anime> animeStaff;

    @Override
    public String toString() {
        return "StaffPage{" +
                "animeStaff=" + animeStaff +
                '}';
    }
}
