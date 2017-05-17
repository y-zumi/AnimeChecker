package com.example.owner_pc.animechecker;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.owner_pc.animechecker.databinding.ActivityAnimeListBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AnimeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_anime_list);
        ActivityAnimeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_list);
        Anime anime = new Anime(1, "ドラえもん");
        binding.setAnime(anime);

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        final AniListService aniListService = ((AnimeCheckerApplication) getApplication()).getAniListService();


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://anilist.co/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        AniListService aniListService = retrofit.create(AniListService.class);
        aniListService.requestToken("client_credentials","y0zumi-ajwul","KpyrM3RTS0RAt0QIs1AmCR9Q4").enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, retrofit2.Response<Token> response) {
                if (response.isSuccessful()) {
                    //通信結果をオブジェクトで受け取る
                    Log.d("RETROFIT_TEST", response.body().accessToken);
                } else {
                    //通信が成功したが、エラーcodeが返ってきた場合はこちら
                    Log.d("RETROFIT_TEST", "error_code" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });

    }
}
