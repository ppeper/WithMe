package com.bonobono.presentation.utils

import android.Manifest
import android.os.Build

object PermissionUtils {
    val CAMERA_PERMISSIONS = listOf(Manifest.permission.CAMERA)
    val LOCATION_PERMISSIONS = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    val GALLERY_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES,
        )
    } else {
        listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }
}