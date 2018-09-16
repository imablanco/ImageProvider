package com.ablanco.imageprovider

import android.content.Context
import android.net.Uri
import android.support.v4.content.FileProvider
import java.io.File

/**
 * Created by Álvaro Blanco Cabrero on 16/09/2018.
 * ImageProvider.
 */
internal object UriUtils {

    private const val fileProviderPath = "image_provider_files"
    private const val fileProviderAuthoritiesSuffix = "image_provider"

    /**
     * Returns a content:// Uri pointing to a temporary file.
     */
    fun createTempContentUri(context: Context): Uri? =
        try {
            val directory = File(context.cacheDir, fileProviderPath).apply { mkdir() }
            //create temp file inside external cache dir/path making the dir if needed
            val file = File.createTempFile(
                System.currentTimeMillis().toString(),
                ".jpg",
                directory
            )
            getFileContentUri(context, file)
        } catch (e: Exception) {
            null
        }

    /**
     * Returns a content:// Uri to allow external apps to access represented File.
     * @param file The file to extract its content Uri. Note that the File passed MUST be located
     * inside fileProviderPath directory.
     * This method assumes that.
     */
    private fun getFileContentUri(context: Context, file: File): Uri? =
        try {
            //return compliant content:// uri for our file
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.$fileProviderAuthoritiesSuffix",
                file
            )

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}