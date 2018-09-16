package com.ablanco.imageprovider

import android.graphics.Bitmap

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
interface ImageProviderSource {

    fun getImage(callback: (Bitmap?) -> Unit)
}