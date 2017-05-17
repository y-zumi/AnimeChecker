package com.example.owner_pc.animechecker;

import android.app.Application;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by owner-PC on 2017/05/15.
 */

public class AnimeCheckerApplication extends Application {
    private Retrofit retrofit;
    private AniListService aniListService;

    @Override
    public void onCreate() {
        super.onCreate();
        // どのActivityからでもAPIを利用できるように、このクラスでAPIを利用する
        setupAPIClient();
    }

    private void setupAPIClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API LOG", message);
            }
        });

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://anilist.co/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        aniListService = retrofit.create(AniListService.class);
    }

    public AniListService getAniListService() {
        return aniListService;
    }
}