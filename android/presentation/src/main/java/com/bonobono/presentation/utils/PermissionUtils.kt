package com.bonobono.presentation.utils

object PermissionUtils {
    val CAMERA_PERMISSIONS = listOf(android.Manifest.permission.CAMERA)
    val LOCATION_PERMISSIONS = listOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )
}