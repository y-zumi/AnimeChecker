package com.example.owner_pc.animechecker.model;

import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimePage;
import com.example.owner_pc.animechecker.model.entity.Character;
import com.example.owner_pc.animechecker.model.entity.Staff;
import com.example.owner_pc.animechecker.model.entity.StaffPage;
import com.example.owner_pc.animechecker.model.entity.StudioPage;
import com.example.owner_pc.animechecker.model.entity.Token;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by owner-PC on 2017/05/15.
 */

public interface AniListService {
    // アクセストークン取得
    @POST("auth/access_token")
//    Call<Token> requestToken(@Query("grant_type") String grantType,
    Observable<Token> requestToken(@Query("grant_type") String grantType,
                                   @Query("client_id") String clientId,
                                   @Query("client_secret") String clientSecret);

    // 指定時期のアニメ一覧
    @GET("browse/anime/?sort=popularity-desc&full_page=ture")
    Observable<List<Anime>> listAnimes(@Query("year") String year,
                                       @Query("season") String season,
                                       @Query("type") String type,
                                       @Query("access_token") String token);

    // アニメ詳細画面の概要(声優、スタッフ、制作会社、各種リンク含む)
    @GET("anime/{id}/page")
    Observable<AnimePage> detailAnime(@Path("id") int id,
                                      @Query("access_token") String token);

    // キャラクターの詳細情報(日本語取得のため)
    @GET("character/{id}")
    Observable<Character> detailCharacter(@Path("id") int id,
                                          @Query("access_token") String token);

    // スタッフ,声優の詳細情報(日本語取得のため)
    @GET("staff/{id}")
    Observable<Staff> detailStaff(@Path("id") int id,
                                  @Query("access_token") String token);

    // 監督の関連作品
    @GET("staff/{id}/page")
    Observable<StaffPage> listDirectorAnimes(@Path("id") int id,
                                             @Query("access_token") String token);

    // 制作会社の関連作品
    @GET("studio/{id}/page")
    Observable<StudioPage> listStudioAnimes(@Path("id") int id,
                                            @Query("access_token") String token);
}

