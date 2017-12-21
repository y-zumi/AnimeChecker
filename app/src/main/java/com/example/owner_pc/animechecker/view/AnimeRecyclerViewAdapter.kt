package com.example.owner_pc.animechecker.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeListFragmentContract
import com.example.owner_pc.animechecker.databinding.FragmentItemGridBinding
import com.example.owner_pc.animechecker.databinding.FragmentItemHorizontalBinding
import com.example.owner_pc.animechecker.model.entity.AnimeCard
import com.example.owner_pc.animechecker.view.AnimeItemFragment.OnListFragmentInteractionListener
import com.example.owner_pc.animechecker.viewmodel.AnimeItemViewModel

class AnimeRecyclerViewAdapter(private val mListener: OnListFragmentInteractionListener, private val view: AnimeListFragmentContract, private val viewType: Int) : RecyclerView.Adapter<AnimeRecyclerViewAdapter.AnimeViewHolder>() {
    private var items: List<AnimeCard>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        when (this.viewType) {
            VIEWTYPE_GRID -> {
                val bindingGrid = DataBindingUtil.inflate<FragmentItemGridBinding>(LayoutInflater.from(parent.context), R.layout.fragment_item_grid, parent, false)
                bindingGrid.viewModel = AnimeItemViewModel(view)
                val bindingViewModel = bindingGrid.viewModel!!
                return AnimeViewHolder(bindingGrid.root, bindingViewModel)
            }
            VIEWTYPE_HORIZONTAL -> {
                val bindingHorizontal = DataBindingUtil.inflate<FragmentItemHorizontalBinding>(LayoutInflater.from(parent.context), R.layout.fragment_item_horizontal, parent, false)
                bindingHorizontal.viewModel = AnimeItemViewModel(view)
                val bindingViewModel = bindingHorizontal.viewModel!!
                return AnimeViewHolder(bindingHorizontal.root, bindingViewModel)
            }
        }
        val binding = DataBindingUtil.inflate<FragmentItemGridBinding>(LayoutInflater.from(parent.context), R.layout.fragment_item_grid, parent, false)
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

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    /**
     * Viewを保持しておくクラス
     * ここではViewModelを持つ
     */
    class AnimeViewHolder(val mView: View, private val viewModel: AnimeItemViewModel) : RecyclerView.ViewHolder(mView) {
        fun loadItem(item: AnimeCard) {
            viewModel.loadItem(item)
        }
    }

    companion object {
        val VIEWTYPE_GRID = 1
        val VIEWTYPE_HORIZONTAL = 2
    }
}
