package com.ablanco.imageprovider

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
internal class GalleryImageSource(private val activity: Activity) : ImageProviderSource {

    private val requestHandler: RequestHandler by lazy {
        RequestHandler()
    }

    override fun getImage(callback: (Bitmap?) -> Unit) {
        val intent = Intent(Intent.ACTION_PICK).setType("image/*")
        requestHandler.startForResult(activity, intent) { result, data ->
            callback(if (result == Activity.RESULT_OK) onImageResult(data) else null)
        }
    }

    private fun onImageResult(data: Intent?): Bitmap? {
        val uri = data?.data ?: return null
        return runCatching {
            activity.contentResolver.openFileDescriptor(uri, "r")?.use {
                BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
            }
        }.getOrNull()
    }

}