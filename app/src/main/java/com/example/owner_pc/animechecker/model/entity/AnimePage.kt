package com.example.owner_pc.animechecker.model.entity

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by owner-PC on 2017/05/12.
 */

class AnimePage : Anime(), Serializable {

    var characters: ArrayList<Characters>? = null
    var staff: ArrayList<StaffSmall>? = null
    var studio: ArrayList<Studio>? = null
    var externalLinks: ArrayList<ExternalLinks>? = null

    val casts: List<String>
        get() {
            val list = ArrayList<String>()
            for (i in characters!!.indices) {
                val str = StringBuilder()
                str.append(characters!![i].nameLast).append(characters!![i].nameFirst).append("\n")
                if (characters!![i].actor!!.size > 0) {
                    str.append(characters!![i].actor!![0].nameLast).append(characters!![i].actor!![0].nameFirst)
                }
                list.add(str.toString())
            }
            return list
        }

    val staffs: List<String>
        get() {
            val list = ArrayList<String>()
            for (i in staff!!.indices) {
                list.add(staff!![i].role + "\n" +
                        staff!![i].nameLast + staff!![i].nameFirst)
            }
            return list
        }

    val director: StaffSmall
        get() {
            for (i in staff!!.indices) {
                if (staff!![i].role == "Director") {
                    return staff!![i]
                }
            }
            return StaffSmall()
        }


    val officialSite: String?
        get() {
            for (i in externalLinks!!.indices) {
                if (externalLinks!![i].site == "Official Site") {
                    return externalLinks!![i].url
                }
            }
            return ""
        }

    val twitterUrl: String?
        get() {
            for (i in externalLinks!!.indices) {
                if (externalLinks!![i].site == "Twitter") {
                    return externalLinks!![i].url
                }
            }
            return ""
        }

    override fun toString(): String {
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
                '}'
    }
}
