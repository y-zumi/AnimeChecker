package com.example.owner_pc.animechecker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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




public class AnimeListActivity extends AppCompatActivity implements AnimeListViewContract {
//    String accessToken;
    private AnimeAdapter animeAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAnimeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_list);
        final AniListService aniListService = ((AnimeCheckerApplication) getApplication()).getAniListService();
        final AnimeListViewModel viewModel = new AnimeListViewModel((AnimeListViewContract) this, aniListService, this);
        binding.setViewModel(viewModel);
        setupViews();
        viewModel.fetchToken();
//        setContentView(R.layout.activity_anime_list);
//        final ActivityAnimeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_list);
//        Anime anime = new Anime(1, "ドラえもん");
//        binding.setAnime(anime);

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        final AniListService aniListService = ((AnimeCheckerApplication) getApplication()).getAniListService();


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://anilist.co/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        AniListService aniListService = retrofit.create(AniListService.class);
/*        aniListService.requestToken("client_credentials","y0zumi-ajwul","KpyrM3RTS0RAt0QIs1AmCR9Q4").enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, retrofit2.Response<Token> response) {
                if (response.isSuccessful()) {
                    //通信結果をオブジェクトで受け取る
                    accessToken = response.body().accessToken;
                    Log.d("RETROFIT_TEST", accessToken);

                    aniListService
//                            .series("21856", accessToken)
//                            .listSeries("2017", "spring", "TV", accessToken)
//                            .detailAnime("21856", accessToken)
                            .detailAnime("21856", accessToken)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<Anime>() {
//                            .subscribe(new Observer<List<Anime>>() {
                            .subscribe(new Observer<AnimePage>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    Log.d("SERIES","subscribe");

                                }

                                @Override
//                                public void onNext(@NonNull Anime anime) {
//                                public void onNext(@NonNull List<Anime> anime) {
                                public void onNext(@NonNull AnimePage page) {
//                                    for (int i = 0; i < series.size(); i++) {
//                                        Log.d("SERIES",series.get(i).toString());
//                                    }
                                    for (int i = 0; i < page.staff.size(); i++) {
                                        Log.d("SERIES",page.staff.get(i).toString());
                                    }
//                                    Log.d("SERIES",page.animeStaff.get(0).toString());
//                                    Log.d("SERIES",series.toString());
//                                    Anime anime = new Anime(series.id, series.titleJapanese);
//                                    binding.setAnime(anime);
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e("SERIES",e.toString());

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
*/
    }
    /**
     * リストなどの画面の要素を作る
     */
    private void setupViews() {
        // ツールバーのセット
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Recycler View
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_animes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        animeAdapter = new AnimeAdapter((Context) this, (AnimeListViewContract) this);
        recyclerView.setAdapter(animeAdapter);

        // SnackBar表示で利用する
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        // Spinner
//        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
//        adapter.addAll("java", "objective-c", "swift", "groovy", "python", "ruby", "c");
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        languageSpinner.setAdapter(adapter);
    }

    // =====RepositoryListViewContract の実装=====
    // ここでPresenterから指示を受けてViewの変更などを行う

    @Override
    public void startDetailActivity(int id) {
//        DetailActivity.start(this, full_name);
    }

    @Override
    public void showAnimes(List<Anime> animes) {
        animeAdapter.setItemsAndRefresh(animes);
    }

    @Override
    public void showError() {
        Snackbar.make(coordinatorLayout, "読み込めませんでした。", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
