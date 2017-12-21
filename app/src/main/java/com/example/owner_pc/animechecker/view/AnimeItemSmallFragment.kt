package com.example.owner_pc.animechecker.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeSmallListFragmentContract
import com.example.owner_pc.animechecker.model.entity.Anime

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
class AnimeItemSmallFragment : Fragment(), AnimeSmallListFragmentContract {
    private lateinit var mListener: OnListFragmentInteractionListener
    private lateinit var adapter: AnimeSmallRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_anime_list_small, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.HORIZONTAL // ここで横方向に設定
            view.layoutManager = manager
            adapter = AnimeSmallRecyclerViewAdapter(mListener, this as AnimeSmallListFragmentContract)
            view.adapter = adapter
        }

        return view
    }

    fun showAnimes(animes: List<Anime>) {
        adapter.setItemsAndRefresh(animes)
    }

    override fun showError() {

    }

    override fun itemClicked(item: Anime) {
        mListener.onListFragmentInteraction(item)
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
        fun onListFragmentInteraction(item: Anime)
    }

    companion object {

        // TODO: Customize parameter initialization
        fun newInstance(): AnimeItemSmallFragment {
            val fragment = AnimeItemSmallFragment()
            val args = Bundle()
            //        args.putInt(ARG_COLUMN_COUNT, columnCount);
            fragment.arguments = args
            return fragment
        }
    }
}
