package com.example.myapplicationtest.extends

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat

fun ImageView.setTintColor(color: Int) {
    val context = this.context
    this.setColorFilter(
        ContextCompat.getColor(
            context,
            color
        )
    )
}