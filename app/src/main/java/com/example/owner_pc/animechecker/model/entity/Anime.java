package com.example.owner_pc.animechecker.model.entity;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/21.
 */

public class Anime implements Serializable {
    public int id;
    public String seriesType;
    public String titleRomaji;
    public String titleEnglish;
    public String titleJapanese;
    public String type;
    public String startDate;
    public String endDate;
    public int startDateFuzzy;
    public int endDateFuzzy;
    public int season;
    public String description;
    public ArrayList<String> synonyms;
    public ArrayList<String> genres;
    public Boolean adult;
    public double averageScore;
    public int popularity;
    public Boolean favorite;
    public String imageUrlSml;
    public String imageUrlMed;
    public String imageUrlLge;
    public String imageUrlBanner;
    public int updateAt;
    //    public ArrayList<> scoreDistribution;
//    public ArrayList<> listStats;
    public int totalEpisodes;
    public int duration;
    public String airingStatus;
    public String youtubeId;
    public String hashtag;
    public String source;

//    public ArrayList<String> airingStats;

    public String role;


    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", seriesType='" + seriesType + '\'' +
                ", titleRomaji='" + titleRomaji + '\'' +
                ", titleEnglish='" + titleEnglish + '\'' +
                ", titleJapanese='" + titleJapanese + '\'' +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startDateFuzzy=" + startDateFuzzy +
                ", endDateFuzzy=" + endDateFuzzy +
                ", season=" + season +
                ", description='" + description + '\'' +
                ", synonyms=" + synonyms +
                ", genres=" + genres +
                ", adult=" + adult +
                ", averageScore=" + averageScore +
                ", popularity=" + popularity +
                ", favorite=" + favorite +
                ", imageUrlSml='" + imageUrlSml + '\'' +
                ", imageUrlMed='" + imageUrlMed + '\'' +
                ", imageUrlLge='" + imageUrlLge + '\'' +
                ", imageUrlBanner='" + imageUrlBanner + '\'' +
                ", updateAt=" + updateAt +
                ", totalEpisodes=" + totalEpisodes +
                ", duration=" + duration +
                ", airingStatus='" + airingStatus + '\'' +
                ", youtubeId='" + youtubeId + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", source='" + source + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
