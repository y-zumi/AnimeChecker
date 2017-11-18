package com.example.owner_pc.animechecker.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owner_pc.animechecker.R;
import com.example.owner_pc.animechecker.contract.AnimeListFragmentContract;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.model.entity.AnimePage;

import java.util.List;

import static com.example.owner_pc.animechecker.view.AnimeRecyclerViewAdapter.VIEWTYPE_GRID;
import static com.example.owner_pc.animechecker.view.AnimeRecyclerViewAdapter.VIEWTYPE_HORIZONTAL;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AnimeItemFragment extends Fragment implements AnimeListFragmentContract {

    private static final String ARG_VIEW_TYPE = "view-type";
    private int viewType = VIEWTYPE_GRID;
    private OnListFragmentInteractionListener mListener;
    private AnimeRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AnimeItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AnimeItemFragment newInstance(int viewType) {
        AnimeItemFragment fragment = new AnimeItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_VIEW_TYPE, viewType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            viewType = getArguments().getInt(ARG_VIEW_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            switch (this.viewType) {
                case VIEWTYPE_GRID:
                    recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                    break;
                case VIEWTYPE_HORIZONTAL:
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.HORIZONTAL); // ここで横方向に設定
                    recyclerView.setLayoutManager(manager);
                    break;
            }
//            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            adapter = new AnimeRecyclerViewAdapter(mListener, (AnimeListFragmentContract) this, this.viewType);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public void showAnimes(List<AnimeCard> animes) {
        adapter.setItemsAndRefresh(animes);
    }

    @Override
    public void itemClicked(AnimePage animePage) {
        mListener.onListFragmentInteraction(animePage);
    }

    @Override
    public void showError() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(AnimePage item);
    }
}
