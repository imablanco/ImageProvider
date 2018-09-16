package com.ablanco.imageprovider

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */

fun Uri.toBitmap(context: Context): Bitmap? {
    try {
        return context.contentResolver.openFileDescriptor(this, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}

/**
 * Turns receiver Bitmap into a File
 * @param path Specified path segment in which the File will be located (will be created in cache dir),
 * if no specified, the File will be located inside the default temporary directory.
 * Note: in order to get a content:// Uri for this File, the path supplied must be the same as defined
 * in the FileProvider that creates the content Uris
 */
fun Bitmap.toFile(context: Context, path: String? = null): File? =
    try {
        //create directory if path is specified
        val directory = path?.let { File(context.cacheDir, it).apply { mkdir() } }
        //create temp file inside external cache dir/path making the dir if needed
        val file = File.createTempFile(
            System.currentTimeMillis().toString(),
            ".jpeg",
            directory
        )
        //save the bitmap to file and return it
        file.apply {
            FileOutputStream(this).use {
                compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

fun Bitmap.applyMatrix(matrix: Matrix): Bitmap =
    Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)