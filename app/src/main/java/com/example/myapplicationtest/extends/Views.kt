package com.example.myapplicationtest.extends

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat

fun ImageView.setTintColor(context: Context, color: Int) {
    this.setColorFilter(
        ContextCompat.getColor(
            context,
            color
        )
    )
}