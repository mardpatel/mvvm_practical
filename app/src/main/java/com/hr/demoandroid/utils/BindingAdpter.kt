package com.hr.demoandroid.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.hr.demoandroid.R

val requestOptions by lazy {
    RequestOptions().apply {
        signature(ObjectKey(System.currentTimeMillis()))
    }
}

@BindingAdapter("img_url")
fun ImageView.setImageByUrl(url: String?) {
    try {
        Glide.with(context).load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .apply(requestOptions)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}