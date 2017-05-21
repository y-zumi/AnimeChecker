package com.example.owner_pc.animechecker;

import java.util.ArrayList;

/**
 * Created by owner-PC on 2017/05/12.
 */

public class AnimePage extends Anime {

    public ArrayList<Characters> characters;
    public ArrayList<StaffSmall> staff;
    public ArrayList<Studio> studio;
    public ArrayList<ExternalLinks> externalLinks;

    @Override
    public String toString() {
        return "AnimePage{" +
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
                ", characters=" + characters +
                ", staff=" + staff +
                ", studio=" + studio +
                ", externalLinks=" + externalLinks +
                '}';
    }
}
