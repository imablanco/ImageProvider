package com.ablanco.imageprovider

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
internal class CameraImageSource(private val activity: Activity) : ImageProviderSource {

    private var photoFileUri: Uri? = null

    private val requestHandler: RequestHandler by lazy {
        RequestHandler()
    }

    override fun getImage(callback: (Bitmap?) -> Unit) {
        /*Create a content:// Uri to let Camera app to store the photo*/
        photoFileUri = UriUtils.createTempContentUri(activity)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFileUri?.let { cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri) }
        requestHandler.startForResult(activity, cameraIntent) { result, _ ->
            callback(if (result == Activity.RESULT_OK) onImageResult() else null)
        }
    }

    private fun onImageResult(): Bitmap? {
        val uri = photoFileUri ?: return null
        return runCatching {
            BitmapFactory.decodeStream(activity.contentResolver.openInputStream(uri))
        }.map { image ->
            ExifInterfaceHelper.fromUri(activity, uri)?.let {
                val matrix = Matrix().apply { postRotate(it.orientation.toFloat()) }
                image.applyMatrix(matrix)
            } ?: image
        }.getOrNull()
    }
}