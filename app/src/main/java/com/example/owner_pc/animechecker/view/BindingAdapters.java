package com.example.owner_pc.animechecker.view;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

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