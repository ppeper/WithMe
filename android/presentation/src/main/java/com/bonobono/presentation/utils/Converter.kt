package com.bonobono.presentation.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

object Converter {
    fun getRealPathFromUriOrNull(context: Context, uri: Uri): String? {
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        return if (cursor == null) {
            uri.path
        } else {
            cursor.moveToFirst()
            val index: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            val realPath: String = cursor.getString(index)
            cursor.close()
            Log.d("TEST", "getRealPathFromUriOrNull: $realPath")
            realPath
        }
    }

}


