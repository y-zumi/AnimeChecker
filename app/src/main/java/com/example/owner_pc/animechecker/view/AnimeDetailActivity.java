package com.example.owner_pc.animechecker.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.owner_pc.animechecker.R;
import com.example.owner_pc.animechecker.contract.AnimeDetailContract;
import com.example.owner_pc.animechecker.databinding.ActivityAnimeDetailBinding;
import com.example.owner_pc.animechecker.model.AniListService;
import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;
import com.example.owner_pc.animechecker.viewmodel.AnimeDetailViewModel;

import java.util.List;

public class AnimeDetailActivity extends AppCompatActivity implements AnimeDetailContract, AnimeItemSmallFragment.OnListFragmentInteractionListener {
//    private static final String EXTRA_FULL_REPOSITORY_NAME = "EXTRA_FULL_REPOSITORY_NAME";
//    private String fullRepoName;
    private AnimeItemSmallFragment directorAnimesFragment;
    private AnimeItemSmallFragment studioAnimesFragment;
    private static final String EXTRA_ANIME_PAGE = "EXTRA_ANIME_PAGE";
    private AnimePage animePage = new AnimePage();

    /**
     * DetailActivityを開始するメソッド
     * @param animePage 詳細のアニメ情報
     */
    public static void start(Context context, AnimePage animePage) {
        final Intent intent = new Intent(context, AnimeDetailActivity.class);
        intent.putExtra(EXTRA_ANIME_PAGE, animePage);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityAnimeDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_detail);
        final AniListService aniListService = ((AnimeCheckerApplication) getApplication()).getAniListService();
        final AnimeDetailViewModel detailViewModel = new AnimeDetailViewModel((AnimeDetailContract) this, aniListService, this);
        binding.setViewModel(detailViewModel);

        final Intent intent = getIntent();
        animePage = (AnimePage) intent.getSerializableExtra(EXTRA_ANIME_PAGE);
        detailViewModel.loadAnimeDetail();
        setupViews();
//        detailViewModel.loadRepositories();
    }

    private void setupViews() {

        directorAnimesFragment = AnimeItemSmallFragment.newInstance();
        studioAnimesFragment = AnimeItemSmallFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.director_animes, directorAnimesFragment);
        transaction.add(R.id.studio_animes, studioAnimesFragment);
        transaction.commit();


    }

    @Override
    public AnimePage getAnimePage() {
        return animePage;
    }


    /**
     * @throws Exception
     */
    @Override
    public void startBrowser(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    //// TODO: 2017/05/29 AnimeCardではなくAnimeで渡した場合に変更する
    @Override
    public void showDirectorAnimes(List<Anime> animes) {
        directorAnimesFragment.showAnimes(animes);
    }

    //// TODO: 2017/05/29 AnimeCardではなくAnimeで渡した場合に変更する
    @Override
    public void showStudioAnimes(List<Anime> animes) {
        studioAnimesFragment.showAnimes(animes);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }

    //// TODO: 2017/05/29 あたらしいdetailActivity立ち上げ
    @Override
    public void onListFragmentInteraction(Anime item) {
        
    }
}
