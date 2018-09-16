package com.ablanco.imageprovider

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap

/**
 * Created by Álvaro Blanco Cabrero on 24/05/2018.
 * ImageProvider.
 */
class ImageProvider(private val activity: Activity){

    private var currentSelectedSource: ImageProviderSource? = null

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Bitmap? {
        return currentSelectedSource?.onImageResult(requestCode, resultCode, data)
    }

    @SuppressLint("SwitchIntDef")
    fun getImage(imageSource: ImageSource) {
        currentSelectedSource = when (imageSource) {
            ImageSource.CAMERA -> CameraImageSource(activity)
            ImageSource.GALLERY -> GalleryImageSource(activity)
        }
        currentSelectedSource?.getImage()
    }
}