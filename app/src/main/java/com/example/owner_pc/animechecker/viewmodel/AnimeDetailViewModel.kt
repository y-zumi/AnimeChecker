package com.example.owner_pc.animechecker.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.databinding.ObservableField
import android.view.View
import com.example.owner_pc.animechecker.contract.AnimeDetailContract
import com.example.owner_pc.animechecker.model.AniListService
import com.example.owner_pc.animechecker.model.entity.AnimePage
import com.example.owner_pc.animechecker.model.entity.StaffPage
import com.example.owner_pc.animechecker.model.entity.StudioPage
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by owner-PC on 2017/05/22.
 */

class AnimeDetailViewModel(internal val detailView: AnimeDetailContract, private val aniListService: AniListService, context: Context) {
    private val preferences: SharedPreferences
    var animeBannerUrl = ObservableField<String>()
    var animeIconUrl = ObservableField<String>()
    var animeTitle = ObservableField<String>()
    var animeSeason = ObservableField<String>()
    var animeDirector = ObservableField<String>()
    var animeStudio = ObservableField<String>()
    var castList = ObservableField<List<String>>()
    var staffList = ObservableField<List<String>>()
    private var animePage: AnimePage? = null

    init {
        this.preferences = context.getSharedPreferences("access_token", Context.MODE_PRIVATE)

    }

    fun prepare() {
        loadAnimeDetail()
    }

    /**
     * 一つのリポジトリについての情報を取得する
     * 基本的にAPIアクセス方法についてはRepositoryListActivity#loadRepositories(String)と同じ
     */
    fun loadAnimeDetail() {
        val item = detailView.animePage
        setAnimeData(item)

        // Retrofitを利用してサーバーにアクセスする
        // 監督の関連アニメを取得
        if (item.director.id != -1) {
            val observableStaff = aniListService.listDirectorAnimes(item.director.id, preferences.getString("token", ""))
            // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
            observableStaff.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<StaffPage> {
                override fun onSubscribe(@NonNull d: Disposable) {

                }

                override fun onNext(@NonNull item: StaffPage) {
                    // TODO: 2017/05/29 List<Anime>のまま渡せるようにする
                    detailView.showDirectorAnimes(item.animeStaff!!)
                }

                override fun onError(@NonNull e: Throwable) {
                    detailView.showError("監督の関連作品を読み込めませんでした")
                }

                override fun onComplete() {}
            })
        }

        //FIXME 非null表明を使用しない
        val studio = item.studio.orEmpty()
        if (studio.isNotEmpty()) {
            val observableStudio = aniListService.listStudioAnimes(studio[0].id, preferences.getString("token", ""))
            // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
            observableStudio.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<StudioPage> {
                override fun onSubscribe(@NonNull d: Disposable) {

                }

                override fun onNext(@NonNull item: StudioPage) {
                    detailView.showStudioAnimes(item.anime!!)
                }

                override fun onError(@NonNull e: Throwable) {
                    detailView.showError("制作会社の関連作品を読み込めませんでした")
                }

                override fun onComplete() {}
            })
        }
    }

    // データをbind
    private fun setAnimeData(item: AnimePage) {
        this.animePage = item
        animeBannerUrl.set(item.imageUrlBanner)
        animeIconUrl.set(item.imageUrlMed)
        animeTitle.set(item.titleJapanese)
        animeSeason.set(item.getSeason())
        if (item.director != null)
            animeDirector.set(item.director.nameLast + item.director.nameFirst)
        else
            animeDirector.set("")
        val studio = item.studio.orEmpty()
        if (studio.isNotEmpty()) {
            animeStudio.set(studio[0].studioName)
        } else {
            animeStudio.set("")
        }
        castList.set(item.casts)
        staffList.set(item.staffs)
    }


    fun onOfficialSiteClick(v: View) {
        try {
            detailView.startBrowser(animePage?.officialSite ?: "")
        } catch (e: Exception) {
            detailView.showError("リンクを開けませんでした。")
        }

    }

    fun onTwitterClick(v: View) {
        try {
            detailView.startBrowser(animePage?.twitterUrl ?: "")
        } catch (e: Exception) {
            detailView.showError("リンクを開けませんでした。")
        }

    }
}
