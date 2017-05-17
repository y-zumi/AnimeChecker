package com.example.owner_pc.animechecker;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/15.
 */

public class Series {
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

    public Series(int id, String seriesType, String titleRomaji, String titleEnglish, String titleJapanese, String type, String startDate, String endDate, int startDateFuzzy, int endDateFuzzy, int season, String description, ArrayList<String> synonyms, ArrayList<String> genres, Boolean adult, double averageScore, int popularity, Boolean favorite, String imageUrlSml, String imageUrlMed, String imageUrlLge, String imageUrlBanner, int updateAt, int totalEpisodes, int duration, String airingStatus, String youtubeId, String hashtag, String source) {
        this.id = id;
        this.seriesType = seriesType;
        this.titleRomaji = titleRomaji;
        this.titleEnglish = titleEnglish;
        this.titleJapanese = titleJapanese;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateFuzzy = startDateFuzzy;
        this.endDateFuzzy = endDateFuzzy;
        this.season = season;
        this.description = description;
        this.synonyms = synonyms;
        this.genres = genres;
        this.adult = adult;
        this.averageScore = averageScore;
        this.popularity = popularity;
        this.favorite = favorite;
        this.imageUrlSml = imageUrlSml;
        this.imageUrlMed = imageUrlMed;
        this.imageUrlLge = imageUrlLge;
        this.imageUrlBanner = imageUrlBanner;
        this.updateAt = updateAt;
        this.totalEpisodes = totalEpisodes;
        this.duration = duration;
        this.airingStatus = airingStatus;
        this.youtubeId = youtubeId;
        this.hashtag = hashtag;
        this.source = source;
    }
}


