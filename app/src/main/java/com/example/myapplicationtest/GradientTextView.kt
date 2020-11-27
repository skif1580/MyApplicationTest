package com.example.myapplicationtest

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

    private val color = ContextCompat.getColor(context, R.color.gradient_text)
    private val color2 = ContextCompat.getColor(context, R.color.gradient_text2)
    private val color3 = ContextCompat.getColor(context, R.color.color_white)
    private val listColor =
        listOf(color, color2, color3).toIntArray()

    init {
        paint.shader = LinearGradient(
            0f, height.toFloat(), 0f, 0f,
            listColor, null,
            Shader.TileMode.CLAMP
        )
    }
}