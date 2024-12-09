package com.example.delivery_project.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.delivery_project.R

fun ImageView.cargarImagen(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.predeterminada)
        .error(R.drawable.error)
        .into(this)
}
