package com.example.owner_pc.animechecker.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.databinding.ObservableInt
import android.view.View
import com.example.owner_pc.animechecker.contract.AnimeListViewContract
import com.example.owner_pc.animechecker.model.AniListService
import com.example.owner_pc.animechecker.model.entity.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by owner-PC on 2017/05/22.
 */

class AnimeListViewModel(private val animeListView: AnimeListViewContract, private val aniListService: AniListService, private val context: Context) {
    val progressBarVisibility = ObservableInt(View.VISIBLE)
    private val preferences: SharedPreferences
    private var animeList: List<Anime> = ArrayList()      // アニメ一覧(監督情報なし)
    private val animeCards = ArrayList<AnimeCard>() // アニメ一覧(監督情報あり)

    init {
        this.preferences = context.getSharedPreferences("access_token", Context.MODE_PRIVATE)
    }

    // アクセストークン取得
    fun fetchToken() {
        val observable = aniListService.requestToken("client_credentials", "y0zumi-ajwul", "KpyrM3RTS0RAt0QIs1AmCR9Q4")
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Token> {
            override fun onSubscribe(@NonNull d: Disposable) {

            }

            override fun onNext(@NonNull token: Token) {
                // トークンを保存
                val editor = preferences.edit()
                editor.putString("token", token.accessToken)
                editor.commit()
            }

            override fun onError(@NonNull e: Throwable) {
                animeListView.showError()
            }

            // token取得後アニメリスト取得
            override fun onComplete() {
                loadAnimes()
            }
        })
    }

    /**
     * 過去一週間で作られたライブラリのスター数順で取得
     */
    fun loadAnimes() {
        // 読込中なのでプログレスバーを表示する
        progressBarVisibility.set(View.VISIBLE)

        // Retrofitを利用してサーバーにアクセスする
        // 2017年春アニメ(TV版)を取得
        val observable = aniListService.listAnimes("2017", "summer", "TV", preferences.getString("token", ""))
        // 入出力(IO)用のスレッドで通信を行い、メインスレッドで結果を受け取るようにする
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<Anime>> {
            override fun onSubscribe(@NonNull d: Disposable) {

            }

            override fun onNext(@NonNull items: List<Anime>) {
                // 今期のアニメリストを格納する(監督情報を格納したアニメリストとの数を一致させるため)
                animeList = items
                // 各アニメの詳細情報を取得
                for (i in items.indices) {
                    loadAnimePage(items[i].id)
                }
            }

            override fun onError(@NonNull e: Throwable) {
                animeListView.showError()
            }

            override fun onComplete() {

            }
        })
    }

    // 監督と制作会社の名称は別途通信が必要である
    fun loadAnimePage(animeId: Int) {
        val observable = aniListService.detailAnime(animeId, preferences.getString("token", ""))
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<AnimePage> {
            override fun onSubscribe(@NonNull d: Disposable) {

            }

            override fun onNext(@NonNull item: AnimePage) {
                // 詳細情報から監督のidを取得し、監督の名前(日本語)を取ってくる
                loadDirector(item.director, item)
            }

            override fun onError(@NonNull e: Throwable) {
                animeListView.showError()
            }

            override fun onComplete() {

            }
        })

    }

    // 監督情報(日本語の名前)を取得
    fun loadDirector(staff: StaffSmall?, animePage: AnimePage) {
        // 監督情報がない場合アニメの情報のみ格納
        if (staff == null || staff.id == -1) {
            animeCards.add(AnimeCard(animePage))
            checkLoadedAnimeList()
            return
        }
        val observable = aniListService.detailStaff(staff.id, preferences.getString("token", ""))
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Staff> {
            override fun onSubscribe(@NonNull d: Disposable) {

            }

            override fun onNext(@NonNull item: Staff) {
                // 監督情報とアニメ情報を格納
                animeCards.add(AnimeCard(animePage, item))
                checkLoadedAnimeList()
            }

            override fun onError(@NonNull e: Throwable) {
                animeListView.showError()
            }

            override fun onComplete() {

            }
        })


    }

    // アニメの詳細情報がすべて取得できたか確認した後、リストを表示
    private fun checkLoadedAnimeList() {
        if (animeList.size == animeCards.size) {
            // 読み込み終了したので、プログレスバーの表示を非表示にする
            progressBarVisibility.set(View.GONE)
            Collections.sort(animeCards) { o1, o2 -> if (o1.animePage.popularity > o2.animePage.popularity) -1 else 1 }
            animeListView.showAnimes(animeCards)
        }
    }

}

