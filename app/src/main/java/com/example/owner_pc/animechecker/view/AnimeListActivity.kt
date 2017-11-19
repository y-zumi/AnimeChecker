package com.example.owner_pc.animechecker.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeListViewContract
import com.example.owner_pc.animechecker.databinding.ActivityAnimeListBinding
import com.example.owner_pc.animechecker.model.AniListService
import com.example.owner_pc.animechecker.model.entity.AnimeCard
import com.example.owner_pc.animechecker.model.entity.AnimePage
import com.example.owner_pc.animechecker.viewmodel.AnimeListViewModel


class AnimeListActivity : AppCompatActivity(), AnimeListViewContract, AnimeItemFragment.OnListFragmentInteractionListener {
    private lateinit var animeFragment: AnimeItemFragment
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var aniListService: AniListService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAnimeListBinding>(this, R.layout.activity_anime_list)
        aniListService = (application as AnimeCheckerApplication).aniListService!!
        val viewModel = AnimeListViewModel(this as AnimeListViewContract, aniListService, this)
        binding.viewModel = viewModel
        setupViews()
        viewModel.fetchToken()
    }

    /**
     * リストなどの画面の要素を作る
     */
    private fun setupViews() {
        // リストFragment配置
        animeFragment = AnimeItemFragment.newInstance(AnimeRecyclerViewAdapter.VIEWTYPE_GRID)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.coordinator_layout, animeFragment)
        transaction.commit()

        // SnackBar表示で利用する
        coordinatorLayout = findViewById(R.id.coordinator_layout) as CoordinatorLayout
    }

    override fun onListFragmentInteraction(item: AnimePage) {
        AnimeDetailActivity.start(this, item)
    }

    // ここでPresenterから指示を受けてViewの変更などを行う

    override fun startDetailActivity(animePage: AnimePage) {
        AnimeDetailActivity.start(this, animePage)
    }

    override fun showAnimes(animes: List<AnimeCard>) {
        animeFragment!!.showAnimes(animes)
    }

    override fun showError() {
        Snackbar.make(coordinatorLayout!!, "読み込めませんでした。", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}
