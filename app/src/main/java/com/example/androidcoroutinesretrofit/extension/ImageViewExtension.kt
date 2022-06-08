package com.example.androidcoroutinesretrofit.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidcoroutinesretrofit.R


val option: RequestOptions = RequestOptions().error(R.mipmap.ic_launcher_round)

/**使用載圖框架*/
fun ImageView.loadImage(url:String?){
    Glide.with(this.context).setDefaultRequestOptions(option).load(url).into(this)
}
