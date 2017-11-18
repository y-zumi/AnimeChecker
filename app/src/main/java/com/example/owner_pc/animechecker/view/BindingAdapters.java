package com.example.owner_pc.animechecker.view;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by owner-PC on 2017/05/22.
 */


public class BindingAdapters {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView, final String imageUrl) {
        // 画像はGlideというライブラリを使ってデータをセットする
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .asBitmap().centerCrop().into(imageView);
//                .asBitmap().into(imageView);
    }
}