package com.example.owner_pc.animechecker.model.entity


import java.io.Serializable
import java.util.*

/**
 * Created by owner-PC on 2017/05/21.
 */

open class Anime : Serializable {
    var id: Int = 0
    var seriesType: String? = null
    var titleRomaji: String? = null
    var titleEnglish: String? = null
    var titleJapanese: String? = null
    var type: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var startDateFuzzy: Int = 0
    var endDateFuzzy: Int = 0
    var season = 0
    var description: String? = null
    var synonyms: ArrayList<String>? = null
    var genres: ArrayList<String>? = null
    var adult: Boolean? = null
    var averageScore: Double = 0.toDouble()
    var popularity: Int = 0
    var favorite: Boolean? = null
    var imageUrlSml: String? = null
    var imageUrlMed: String? = null
    var imageUrlLge: String? = null
    var imageUrlBanner: String? = null
    var updateAt: Int = 0
    //    public ArrayList<> scoreDistribution;
    //    public ArrayList<> listStats;
    var totalEpisodes: Int = 0
    var duration: Int = 0
    var airingStatus: String? = null
    var youtubeId: String? = null
    var hashtag: String? = null
    var source: String? = null

    //    public ArrayList<String> airingStats;

    var role: String? = null


    fun getSeason(): String {
        if (season == 0) {
            return ""
        }
        val year = season / 10
        val month = season % 10
        val str = StringBuilder()
        str.append("20").append(year).append("年")
        when (month) {
            1 -> str.append("冬")
            2 -> str.append("春")
            3 -> str.append("夏")
            4 -> str.append("秋")
        }
        return str.toString()

    }

    override fun toString(): String {
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
                '}'
    }

}
