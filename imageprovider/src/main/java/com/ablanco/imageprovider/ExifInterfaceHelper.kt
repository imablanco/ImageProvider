package com.ablanco.imageprovider

import android.content.Context
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
internal class ExifInterfaceHelper private constructor(inputStream: InputStream) {

    private var exifInterface: ExifInterface? = null

    companion object {
        fun fromFile(file: File): ExifInterfaceHelper? =
            try {
                ExifInterfaceHelper(FileInputStream(file))
            } catch (e: Exception) {
                null
            }

        fun fromUri(context: Context, uri: Uri): ExifInterfaceHelper? = runCatching {
            val inputStream = requireNotNull(context.contentResolver.openInputStream(uri))
            ExifInterfaceHelper(inputStream)
        }.getOrNull()
    }

    init {
        try {
            exifInterface =
                ExifInterface(inputStream)
        } catch (e: IOException) {
            // Handle any errors
        } finally {
            try {
                inputStream.close()
            } catch (ignored: IOException) {
            }
        }
    }

    val orientation: Int
        get() {
            val orientation = exifInterface?.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }
        }

    val isPortrait: Boolean
        get() = orientation == ExifInterface.ORIENTATION_NORMAL
}