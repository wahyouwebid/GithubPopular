package com.ujangwahyu.app.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ujangwahyu.app.R


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.color.grey)
        .into(this)
}

fun String.splitString(): String {
    val parts = this.split(" ")
    return if (parts.size == 2) {
        val first = parts[0].first()
        val second = parts[1].first()
        "${first}${second}"
    } else {
        "${parts[0].first()}"
    }
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}