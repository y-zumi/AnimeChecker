package com.example.owner_pc.animechecker.model.entity;

import java.io.Serializable;

/**
 * Created by owner-PC on 2017/05/21.
 */

public class ExternalLinks implements Serializable {
    public int id;
    public String url;
    public String site;

    @Override
    public String toString() {
        return "ExternalLinks{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
