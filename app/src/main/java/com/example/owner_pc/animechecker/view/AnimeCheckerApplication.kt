package com.example.owner_pc.animechecker.view

import android.app.Application
import android.util.Log
import com.example.owner_pc.animechecker.model.AniListService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by owner-PC on 2017/05/15.
 */

class AnimeCheckerApplication : Application() {
    private var retrofit: Retrofit? = null
    var aniListService: AniListService? = null
        private set

    override fun onCreate() {
        super.onCreate()
        // どのActivityからでもAPIを利用できるように、このクラスでAPIを利用する
        setupAPIClient()
    }

    private fun setupAPIClient() {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("API LOG", message) })

        logging.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl("https://anilist.co/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        aniListService = retrofit!!.create(AniListService::class.java)
    }
}