package com.example.owner_pc.animechecker.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.owner_pc.animechecker.R;
import com.example.owner_pc.animechecker.contract.AnimeListViewContract;
import com.example.owner_pc.animechecker.databinding.ActivityAnimeListBinding;
import com.example.owner_pc.animechecker.model.AniListService;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;
import com.example.owner_pc.animechecker.viewmodel.AnimeListViewModel;

import java.util.List;


public class AnimeListActivity extends AppCompatActivity implements AnimeListViewContract, AnimeItemFragment.OnListFragmentInteractionListener {
    private AnimeItemFragment animeFragment;
    private CoordinatorLayout coordinatorLayout;
    private AniListService aniListService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAnimeListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_list);
        aniListService = ((AnimeCheckerApplication) getApplication()).getAniListService();
        final AnimeListViewModel viewModel = new AnimeListViewModel((AnimeListViewContract) this, aniListService, this);
        binding.setViewModel(viewModel);
        setupViews();
        viewModel.fetchToken();
    }

    /**
     * リストなどの画面の要素を作る
     */
    private void setupViews() {
        // リストFragment配置
        animeFragment = AnimeItemFragment.newInstance(AnimeRecyclerViewAdapter.VIEWTYPE_GRID);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.coordinator_layout, animeFragment);
        transaction.commit();

        // SnackBar表示で利用する
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
    }

    @Override
    public void onListFragmentInteraction(AnimePage item) {
        AnimeDetailActivity.start(this, item);
    }

    // ここでPresenterから指示を受けてViewの変更などを行う

    @Override
    public void startDetailActivity(AnimePage animePage) {
        AnimeDetailActivity.start(this, animePage);
    }

    @Override
    public void showAnimes(List<AnimeCard> animes) {
        animeFragment.showAnimes(animes);
    }

    @Override
    public void showError() {
        Snackbar.make(coordinatorLayout, "読み込めませんでした。", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
