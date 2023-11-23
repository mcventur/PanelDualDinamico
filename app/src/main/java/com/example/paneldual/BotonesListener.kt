package com.example.paneldual

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Interface para observadores de clicks en el BotonesFragment
 */
interface BotonesListener {
    fun onClickButton(@ColorInt color: Int)
}