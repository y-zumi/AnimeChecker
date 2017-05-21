package com.example.owner_pc.animechecker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owner_pc.animechecker.databinding.AnimeItemBinding;

import java.util.List;

/**
 * Created by owner-PC on 2017/05/22.
 */


public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private final AnimeListViewContract view;
    private final Context context;
    private List<Anime> items;

    public AnimeAdapter(Context context, AnimeListViewContract view) {
        this.context = context;
        this.view = view;
    }

    /**
     * リポジトリのデータをセットして更新する
     * @param items
     */
    public void setItemsAndRefresh(List<Anime> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Anime getItemAt(int position) {
        return items.get(position);
    }

    /**
     * RecyclerViewのアイテムのView作成とViewを保持するViewHolderを作成
     */
    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AnimeItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.anime_item, parent, false);
        binding.setViewModel(new AnimeItemViewModel(view));
        return new AnimeViewHolder(binding.getRoot(), binding.getViewModel());
    }

    /**
     * onCreateViewHolderで作ったViewHolderのViewに
     * setItemsAndRefresh(items)でセットされたデータを入れる
     */
    @Override
    public void onBindViewHolder(final AnimeViewHolder holder, final int position) {
        final Anime item = getItemAt(position);
        holder.loadItem(item);

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

        public AnimeViewHolder(View itemView, AnimeItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
        }

        public void loadItem(Anime item) {
            viewModel.loadItem(item);
        }
    }


}
