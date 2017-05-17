package com.example.owner_pc.animechecker;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by owner-PC on 2017/05/15.
 */

public interface AniListService {
    @POST("auth/access_token")
    Call<Token> requestToken(@Query("grant_type") String grantType,
                             @Query("client_id") String clientId,
                             @Query("client_secret") String clientSecret
                        );
    @GET("browse/anime/?sort=popularity-desc&full_page=ture")
    Observable<Series> listSeries(@Query("year") String year,
                                  @Query("season") String season,
                                  @Query("type") String type,
                                  @Query("access_token") String token
    );
    @GET("anime/{id}")
    Observable<Series> series(@Path("id") String id,
                              @Query("access_token") String token);
//    @GET("anime/{id}")
//    Observable<>


//    Observable<Repositories> listRepos(@Query("q") String query);

}

