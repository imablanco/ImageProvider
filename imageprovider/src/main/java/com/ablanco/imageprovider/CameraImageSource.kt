package com.ablanco.imageprovider

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.FragmentActivity

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
internal class CameraImageSource(private val activity: FragmentActivity) : ImageProviderSource {

    private var photoFileUri: Uri? = null

    private val requestHandler: RequestHandler by lazy {
        RequestHandler()
    }

    override fun getImage(callback: (Bitmap?) -> Unit) {
        /*Create a content:// Uri to let Camera app to store the photo*/
        photoFileUri = UriUtils.createTempContentUri(activity)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFileUri?.let { cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri) }
        runCatching {
            requestHandler.startForResult(activity, cameraIntent) { result, _ ->
                callback(if (result == Activity.RESULT_OK) onImageResult() else null)
            }
        }
    }

    private fun onImageResult(): Bitmap? {

        val uri = photoFileUri ?: return null

        return runCatching {
            val image = BitmapFactory.decodeStream(activity.contentResolver.openInputStream(uri))
            ExifInterfaceHelper.fromUri(activity, uri)?.let { helper ->
                val matrix = Matrix().apply { postRotate(helper.orientation.toFloat()) }
                image.applyMatrix(matrix)
            } ?: image
        }.getOrNull()
    }
}
