package com.example.owner_pc.animechecker.view

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide

/**
 * Created by owner-PC on 2017/05/22.
 */


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, imageUrl: String) {
        // 画像はGlideというライブラリを使ってデータをセットする
        Glide.with(imageView.context)
                .load(imageUrl)
                .asBitmap().centerCrop().into(imageView)
        //                .asBitmap().into(imageView);
    }
}