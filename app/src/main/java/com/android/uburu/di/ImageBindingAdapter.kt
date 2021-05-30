package com.android.uburu.di

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageBindingAdapter(private val picasso: Picasso, private val url: String?) {
    fun loadImageUrl(imageView: ImageView?) {
        if (url != null && url.trim { it <= ' ' }.isNotEmpty()) picasso.load(url).into(imageView)
    }
}