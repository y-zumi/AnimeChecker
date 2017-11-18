package com.example.owner_pc.animechecker.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner-PC on 2017/05/12.
 */

public class AnimePage extends Anime implements Serializable {

    public ArrayList<Characters> characters;
    public ArrayList<StaffSmall> staff;
    public ArrayList<Studio> studio;
    public ArrayList<ExternalLinks> externalLinks;

    public List<String> getCasts() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            StringBuilder str = new StringBuilder();
            str.append(characters.get(i).nameLast).append(characters.get(i).nameFirst).append("\n");
            if (characters.get(i).actor.size() > 0) {
                str.append(characters.get(i).actor.get(0).nameLast).append(characters.get(i).actor.get(0).nameFirst);
            }
            list.add(str.toString());
        }
        return list;
    }

    public List<String> getStaffs() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < staff.size(); i++) {
            list.add(staff.get(i).role + "\n" +
                    staff.get(i).nameLast + staff.get(i).nameFirst);
        }
        return list;
    }

    public StaffSmall getDirector() {
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).role.equals("Director")) {
                return staff.get(i);
            }
        }
        return new StaffSmall();
    }


    public String getOfficialSite() {
        for (int i = 0; i < externalLinks.size(); i++) {
            if (externalLinks.get(i).site.equals("Official Site")) {
                return externalLinks.get(i).url;
            }
        }
        return "";
    }

    public String getTwitterUrl() {
        for (int i = 0; i < externalLinks.size(); i++) {
            if (externalLinks.get(i).site.equals("Twitter")) {
                return externalLinks.get(i).url;
            }
        }
        return "";
    }

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
