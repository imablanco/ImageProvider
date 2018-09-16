package com.ablanco.imageprovider

import android.content.Intent
import android.graphics.Bitmap

/**
 * Created by Álvaro Blanco Cabrero on 24/05/2018.
 * ImageProvider.
 */
abstract class ImageProviderSource {

    abstract fun getImage()

    abstract fun onImageResult(requestCode: Int, resultCode: Int, data: Intent?) : Bitmap?
}