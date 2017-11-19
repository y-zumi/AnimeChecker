package com.example.owner_pc.animechecker.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeListFragmentContract
import com.example.owner_pc.animechecker.model.entity.AnimeCard
import com.example.owner_pc.animechecker.model.entity.AnimePage
import com.example.owner_pc.animechecker.view.AnimeRecyclerViewAdapter.Companion.VIEWTYPE_GRID
import com.example.owner_pc.animechecker.view.AnimeRecyclerViewAdapter.Companion.VIEWTYPE_HORIZONTAL


/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class AnimeItemFragment : Fragment(), AnimeListFragmentContract {
    private var viewType = VIEWTYPE_GRID
    private lateinit var mListener: OnListFragmentInteractionListener
    private lateinit var adapter: AnimeRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            viewType = arguments.getInt(ARG_VIEW_TYPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_anime_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            when (this.viewType) {
                VIEWTYPE_GRID -> view.layoutManager = GridLayoutManager(context, 2)
                VIEWTYPE_HORIZONTAL -> {
                    val manager = LinearLayoutManager(context)
                    manager.orientation = LinearLayoutManager.HORIZONTAL // ここで横方向に設定
                    view.layoutManager = manager
                }
            }
            //            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            adapter = AnimeRecyclerViewAdapter(mListener, this as AnimeListFragmentContract, this.viewType)
            view.adapter = adapter
        }
        return view
    }

    fun showAnimes(animes: List<AnimeCard>) {
        adapter.setItemsAndRefresh(animes)
    }

    override fun itemClicked(animePage: AnimePage) {
        mListener.onListFragmentInteraction(animePage)
    }

    override fun showError() {

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: AnimePage)
    }

    companion object {

        private val ARG_VIEW_TYPE = "view-type"

        // TODO: Customize parameter initialization
        fun newInstance(viewType: Int): AnimeItemFragment {
            val fragment = AnimeItemFragment()
            val args = Bundle()
            args.putInt(ARG_VIEW_TYPE, viewType)
            fragment.arguments = args
            return fragment
        }
    }
}
