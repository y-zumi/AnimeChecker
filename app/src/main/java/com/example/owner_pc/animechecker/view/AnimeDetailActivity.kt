package com.example.owner_pc.animechecker.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeDetailContract
import com.example.owner_pc.animechecker.databinding.ActivityAnimeDetailBinding
import com.example.owner_pc.animechecker.model.AniListService
import com.example.owner_pc.animechecker.model.entity.Anime
import com.example.owner_pc.animechecker.model.entity.AnimePage
import com.example.owner_pc.animechecker.viewmodel.AnimeDetailViewModel

class AnimeDetailActivity : AppCompatActivity(), AnimeDetailContract, AnimeItemSmallFragment.OnListFragmentInteractionListener {
    //    private static final String EXTRA_FULL_REPOSITORY_NAME = "EXTRA_FULL_REPOSITORY_NAME";
    //    private String fullRepoName;
    private var directorAnimesFragment: AnimeItemSmallFragment? = null
    private var studioAnimesFragment: AnimeItemSmallFragment? = null
    override var animePage = AnimePage()
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAnimeDetailBinding>(this, R.layout.activity_anime_detail)
        val aniListService = (application as AnimeCheckerApplication).aniListService
        val detailViewModel = AnimeDetailViewModel(this as AnimeDetailContract, aniListService!!, this)
        binding.viewModel = detailViewModel

        val intent = intent
        animePage = intent.getSerializableExtra(EXTRA_ANIME_PAGE) as AnimePage
        detailViewModel.loadAnimeDetail()
        setupViews()
        //        detailViewModel.loadRepositories();
    }

    private fun setupViews() {

        directorAnimesFragment = AnimeItemSmallFragment.newInstance()
        studioAnimesFragment = AnimeItemSmallFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.director_animes, directorAnimesFragment)
        transaction.add(R.id.studio_animes, studioAnimesFragment)
        transaction.commit()


    }


    /**
     * @throws Exception
     */
    override fun startBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    //// TODO: 2017/05/29 AnimeCardではなくAnimeで渡した場合に変更する
    override fun showDirectorAnimes(animes: List<Anime>) {
        directorAnimesFragment!!.showAnimes(animes)
    }

    //// TODO: 2017/05/29 AnimeCardではなくAnimeで渡した場合に変更する
    override fun showStudioAnimes(animes: List<Anime>) {
        studioAnimesFragment!!.showAnimes(animes)
    }

    override fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show()
    }

    //// TODO: 2017/05/29 あたらしいdetailActivity立ち上げ
    override fun onListFragmentInteraction(item: Anime) {

    }

    companion object {
        private val EXTRA_ANIME_PAGE = "EXTRA_ANIME_PAGE"

        /**
         * DetailActivityを開始するメソッド
         *
         * @param animePage 詳細のアニメ情報
         */
        fun start(context: Context, animePage: AnimePage) {
            val intent = Intent(context, AnimeDetailActivity::class.java)
            intent.putExtra(EXTRA_ANIME_PAGE, animePage)
            context.startActivity(intent)
        }
    }
}
