package com.example.owner_pc.animechecker.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeListViewContract
import com.example.owner_pc.animechecker.databinding.AnimeItemBinding
import com.example.owner_pc.animechecker.model.entity.AnimeCard
import com.example.owner_pc.animechecker.viewmodel.AnimeItemViewModel

/**
 * Created by owner-PC on 2017/05/22.
 */

class AnimeAdapter(private val context: Context, private val view: AnimeListViewContract) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    private var items: List<AnimeCard>? = null

    /**
     * リポジトリのデータをセットして更新する
     *
     * @param items
     */
    fun setItemsAndRefresh(items: List<AnimeCard>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): AnimeCard {
        return items!![position]
    }

    /**
     * RecyclerViewのアイテムのView作成とViewを保持するViewHolderを作成
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = DataBindingUtil.inflate<AnimeItemBinding>(LayoutInflater.from(context), R.layout.anime_item, parent, false)
        binding.viewModel = AnimeItemViewModel(view)
        val bindingViewModel = binding.viewModel!!
        return AnimeViewHolder(binding.root, bindingViewModel)
    }

    /**
     * onCreateViewHolderで作ったViewHolderのViewに
     * setItemsAndRefresh(items)でセットされたデータを入れる
     */
    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val item = getItemAt(position)
        holder.loadItem(item)

    }

    override fun getItemCount(): Int {
        return if (items == null) {
            0
        } else items!!.size
    }

    /**
     * Viewを保持しておくクラス
     * ここではViewModelを持つ
     */
    class AnimeViewHolder(itemView: View, private val viewModel: AnimeItemViewModel) : RecyclerView.ViewHolder(itemView) {

        fun loadItem(item: AnimeCard) {
            viewModel.loadItem(item)
        }
    }


}
