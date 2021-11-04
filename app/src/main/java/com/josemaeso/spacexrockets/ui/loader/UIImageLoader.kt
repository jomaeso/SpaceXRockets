package com.josemaeso.spacexrockets.ui.loader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface UIImageLoader {
    fun loadImage(imageUrl: String, view: ImageView, @DrawableRes placeholderResource: Int)
}