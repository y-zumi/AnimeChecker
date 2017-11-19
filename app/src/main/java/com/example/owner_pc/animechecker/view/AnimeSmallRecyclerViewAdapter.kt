package com.example.owner_pc.animechecker.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.owner_pc.animechecker.R
import com.example.owner_pc.animechecker.contract.AnimeSmallListFragmentContract
import com.example.owner_pc.animechecker.databinding.FragmentItemHorizontal1Binding
import com.example.owner_pc.animechecker.model.entity.Anime
import com.example.owner_pc.animechecker.view.AnimeItemSmallFragment.OnListFragmentInteractionListener
import com.example.owner_pc.animechecker.viewmodel.AnimeItemViewModel

class AnimeSmallRecyclerViewAdapter(private val mListener: OnListFragmentInteractionListener, private val view: AnimeSmallListFragmentContract) : RecyclerView.Adapter<AnimeSmallRecyclerViewAdapter.AnimeSmallViewHolder>() {
    private var items: List<Anime>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeSmallViewHolder {
        val bindingHorizontal = DataBindingUtil.inflate<FragmentItemHorizontal1Binding>(LayoutInflater.from(parent.context), R.layout.fragment_item_horizontal1, parent, false)
        bindingHorizontal.viewModel = AnimeItemViewModel(view)
        val bindinngViewModel = bindingHorizontal.viewModel!!
        return AnimeSmallViewHolder(bindingHorizontal.root, bindinngViewModel)
    }

    //    @Override
    //    public AnimeSmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //        View view = LayoutInflater.from(parent.getContext())
    //                .inflate(R.layout.fragment_item_small, parent, false);
    //        return new ViewHolder(view);
    //    }
    override fun onBindViewHolder(holder: AnimeSmallViewHolder, position: Int) {
        val item = getItemAt(position)
        holder.loadItem(item)
    }

    /**
     * リポジトリのデータをセットして更新する
     *
     * @param items
     */
    fun setItemsAndRefresh(items: List<Anime>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Anime {
        return items!![position]
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
    class AnimeSmallViewHolder
    //        public AnimeCard mItem;

    (val mView: View, private val viewModel: AnimeItemViewModel) : RecyclerView.ViewHolder(mView) {

        fun loadItem(item: Anime) {
            viewModel.loadSmallItem(item)
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
