package com.example.owner_pc.animechecker.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.owner_pc.animechecker.R;
import com.example.owner_pc.animechecker.contract.AnimeListFragmentContract;
import com.example.owner_pc.animechecker.contract.AnimeListViewContract;
import com.example.owner_pc.animechecker.databinding.AnimeItemBinding;
import com.example.owner_pc.animechecker.databinding.FragmentItemGridBinding;
import com.example.owner_pc.animechecker.databinding.FragmentItemHorizontalBinding;
import com.example.owner_pc.animechecker.model.entity.AnimeCard;
import com.example.owner_pc.animechecker.view.AnimeItemFragment.OnListFragmentInteractionListener;

import com.example.owner_pc.animechecker.viewmodel.AnimeItemViewModel;

import java.util.List;

public class AnimeRecyclerViewAdapter extends RecyclerView.Adapter<AnimeRecyclerViewAdapter.AnimeViewHolder> {

    public final static int VIEWTYPE_GRID = 1;
    public final static int VIEWTYPE_HORIZONTAL = 2;

    private final AnimeListFragmentContract view;
    private List<AnimeCard> items;
    private int viewType;

//    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

//    public AnimeRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
    public AnimeRecyclerViewAdapter(OnListFragmentInteractionListener listener, AnimeListFragmentContract view, int viewType) {
        mListener = listener;
        this.view = view;
        this.viewType = viewType;
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (this.viewType) {
            case VIEWTYPE_GRID:
                FragmentItemGridBinding bindingGrid = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_item_grid, parent, false);
                bindingGrid.setViewModel(new AnimeItemViewModel(view));
                return new AnimeViewHolder(bindingGrid.getRoot(), bindingGrid.getViewModel());
            case VIEWTYPE_HORIZONTAL:
                FragmentItemHorizontalBinding bindingHorizontal = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_item_horizontal, parent, false);
                bindingHorizontal.setViewModel(new AnimeItemViewModel(view));
                return new AnimeViewHolder(bindingHorizontal.getRoot(), bindingHorizontal.getViewModel());
        }
        FragmentItemGridBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_item_grid, parent, false);
        binding.setViewModel(new AnimeItemViewModel(view));
        return new AnimeViewHolder(binding.getRoot(), binding.getViewModel());
    }

//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    /**
     * onCreateViewHolderで作ったViewHolderのViewに
     * setItemsAndRefresh(items)でセットされたデータを入れる
     */
    @Override
    public void onBindViewHolder(final AnimeViewHolder holder, final int position) {
        final AnimeCard item = getItemAt(position);
        holder.loadItem(item);

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(item);
//                }
//            }
//        });
    }

//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
//    }

    /**
     * リポジトリのデータをセットして更新する
     * @param items
     */
    public void setItemsAndRefresh(List<AnimeCard> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public AnimeCard getItemAt(int position) {
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
    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        private final AnimeItemViewModel viewModel;
        public final View mView;
//        public AnimeCard mItem;

        public AnimeViewHolder(View itemView, AnimeItemViewModel viewModel) {
            super(itemView);
            mView = itemView;
            this.viewModel = viewModel;
        }

        public void loadItem(AnimeCard item) {
            viewModel.loadItem(item);
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
