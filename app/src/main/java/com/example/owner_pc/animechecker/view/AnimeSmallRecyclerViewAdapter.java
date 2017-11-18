package com.example.owner_pc.animechecker.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owner_pc.animechecker.R;
import com.example.owner_pc.animechecker.contract.AnimeSmallListFragmentContract;
import com.example.owner_pc.animechecker.databinding.FragmentItemHorizontal1Binding;
import com.example.owner_pc.animechecker.model.entity.Anime;
import com.example.owner_pc.animechecker.view.AnimeItemSmallFragment.OnListFragmentInteractionListener;
import com.example.owner_pc.animechecker.viewmodel.AnimeItemViewModel;

import java.util.List;

public class AnimeSmallRecyclerViewAdapter extends RecyclerView.Adapter<AnimeSmallRecyclerViewAdapter.AnimeSmallViewHolder> {

    private final AnimeSmallListFragmentContract view;
    private final OnListFragmentInteractionListener mListener;
    private List<Anime> items;

    public AnimeSmallRecyclerViewAdapter(OnListFragmentInteractionListener listener, AnimeSmallListFragmentContract view) {
        mListener = listener;
        this.view = view;
    }

    @Override
    public AnimeSmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemHorizontal1Binding bindingHorizontal = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_item_horizontal1, parent, false);
        bindingHorizontal.setViewModel(new AnimeItemViewModel(view));
        return new AnimeSmallViewHolder(bindingHorizontal.getRoot(), bindingHorizontal.getViewModel());
    }

    //    @Override
//    public AnimeSmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_item_small, parent, false);
//        return new ViewHolder(view);
//    }
    @Override
    public void onBindViewHolder(final AnimeSmallViewHolder holder, final int position) {
        final Anime item = getItemAt(position);
        holder.loadItem(item);
    }

    /**
     * リポジトリのデータをセットして更新する
     *
     * @param items
     */
    public void setItemsAndRefresh(List<Anime> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Anime getItemAt(int position) {
        return items.get(position);
    }


    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    /**
     * Viewを保持しておくクラス
     * ここではViewModelを持つ
     */
    static class AnimeSmallViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private final AnimeItemViewModel viewModel;
//        public AnimeCard mItem;

        public AnimeSmallViewHolder(View itemView, AnimeItemViewModel viewModel) {
            super(itemView);
            mView = itemView;
            this.viewModel = viewModel;
        }

        public void loadItem(Anime item) {
            viewModel.loadSmallItem(item);
        }
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final TextView mIdView;
//        public final TextView mContentView;
//        public DummyItem mItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
//            mContentView = (TextView) view.findViewById(R.id.content);
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
//    }
}
