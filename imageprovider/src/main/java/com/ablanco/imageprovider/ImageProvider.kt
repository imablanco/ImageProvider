package com.ablanco.imageprovider

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
class ImageProvider(private val activity: Activity) {

    private var currentSelectedSource: ImageProviderSource? = null

    @SuppressLint("SwitchIntDef")
    fun getImage(imageSource: ImageSource, callback: (Bitmap?) -> Unit) {
        currentSelectedSource = when (imageSource) {
            ImageSource.CAMERA -> CameraImageSource(activity)
            ImageSource.GALLERY -> GalleryImageSource(activity)
        }
        currentSelectedSource?.getImage(callback)
    }
}