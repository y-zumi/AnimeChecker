package com.example.owner_pc.animechecker.model

import com.example.owner_pc.animechecker.model.entity.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by owner-PC on 2017/05/15.
 */

interface AniListService {
    // アクセストークン取得
    @POST("auth/access_token")
    fun requestToken(@Query("grant_type") grantType: String,
                     @Query("client_id") clientId: String,
                     @Query("client_secret") clientSecret: String):
            //    Call<Token> requestToken(@Query("grant_type") String grantType,
            Observable<Token>

    // 指定時期のアニメ一覧
    @GET("browse/anime/?sort=popularity-desc&full_page=ture")
    fun listAnimes(@Query("year") year: String,
                   @Query("season") season: String,
                   @Query("type") type: String,
                   @Query("access_token") token: String): Observable<List<Anime>>

    // アニメ詳細画面の概要(声優、スタッフ、制作会社、各種リンク含む)
    @GET("anime/{id}/page")
    fun detailAnime(@Path("id") id: Int,
                    @Query("access_token") token: String): Observable<AnimePage>

    // キャラクターの詳細情報(日本語取得のため)
    @GET("character/{id}")
    fun detailCharacter(@Path("id") id: Int,
                        @Query("access_token") token: String): Observable<Character>

    // スタッフ,声優の詳細情報(日本語取得のため)
    @GET("staff/{id}")
    fun detailStaff(@Path("id") id: Int,
                    @Query("access_token") token: String): Observable<Staff>

    // 監督の関連作品
    @GET("staff/{id}/page")
    fun listDirectorAnimes(@Path("id") id: Int,
                           @Query("access_token") token: String): Observable<StaffPage>

    // 制作会社の関連作品
    @GET("studio/{id}/page")
    fun listStudioAnimes(@Path("id") id: Int,
                         @Query("access_token") token: String): Observable<StudioPage>
}

