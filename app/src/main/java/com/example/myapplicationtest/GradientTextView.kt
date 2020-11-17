package com.example.myapplicationtest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.ContextCompat

class GradientTextView : TextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    val color = ContextCompat.getColor(context, R.color.gradientText)
    val color2 = ContextCompat.getColor(context, R.color.gradientText2)
    val color3 = ContextCompat.getColor(context, R.color.gradientText3)
    val listColor =
        listOf(color, color2, color3).toIntArray()

    init {
        paint.shader = LinearGradient(
            0f, textSize, width.toFloat(), 0f,
            listColor, null,
            Shader.TileMode.CLAMP
        )
    }
}