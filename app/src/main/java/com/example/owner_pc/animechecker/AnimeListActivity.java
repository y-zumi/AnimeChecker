package com.example.owner_pc.animechecker;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.owner_pc.animechecker.databinding.ActivityAnimeListBinding;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class AnimeListActivity extends AppCompatActivity {
    String accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_anime_list);
        final ActivityAnimeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_list);
//        Anime anime = new Anime(1, "ドラえもん");
//        binding.setAnime(anime);

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
                    accessToken = response.body().accessToken;
                    Log.d("RETROFIT_TEST", accessToken);

                    aniListService
                            .series("21856", accessToken)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Series>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    Log.d("SERIES","subscribe");

                                }

                                @Override
                                public void onNext(@NonNull Series series) {
//                                    for (int i = 0; i < series.size(); i++) {
//                                        Log.d("SERIES",series.get(i).titleRomaji);
//                                    }
//                                    System.out.println("series titleRomaji");
//                                    System.out.println(series.titleRomaji);
                                    Log.d("SERIES",series.titleJapanese);
                                    Anime anime = new Anime(series.id, series.titleJapanese);
                                    binding.setAnime(anime);

                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d("SERIES",e.toString());

                                }

                                @Override
                                public void onComplete() {
                                    Log.d("SERIES","onComplete");

                                }
                            });
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
