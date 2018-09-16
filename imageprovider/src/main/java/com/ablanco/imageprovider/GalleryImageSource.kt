package com.ablanco.imageprovider

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap

/**
 * Created by Álvaro Blanco Cabrero on 24/05/2018.
 * ImageProvider.
 */
class GalleryImageSource(private val activity: Activity) : ImageProviderSource() {
    override fun getImage() {
        val intent = Intent(Intent.ACTION_PICK).setType("image/*")
        activity.startActivityForResult(
            Intent.createChooser(intent, "TakePhoto"), //TODO
            REQUEST_CODE
        )
    }

    override fun onImageResult(requestCode: Int, resultCode: Int, data: Intent?): Bitmap? {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            return data?.data?.toBitmap(activity)
        }
        return null
    }

    companion object {
        private const val REQUEST_CODE = 1889
    }
}