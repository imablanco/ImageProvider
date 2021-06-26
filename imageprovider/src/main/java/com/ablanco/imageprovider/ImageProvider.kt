package com.ablanco.imageprovider

import android.graphics.Bitmap
import androidx.fragment.app.FragmentActivity

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
class ImageProvider(private val activity: FragmentActivity) {

    private var currentSelectedSource: ImageProviderSource? = null

    fun getImage(imageSource: ImageSource, callback: (Bitmap?) -> Unit) {
        currentSelectedSource = when (imageSource) {
            ImageSource.CAMERA -> CameraImageSource(activity)
            ImageSource.GALLERY -> GalleryImageSource(activity)
        }
        currentSelectedSource?.getImage(callback)
    }
}
