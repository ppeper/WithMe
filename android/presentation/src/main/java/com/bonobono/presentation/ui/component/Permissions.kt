package com.bonobono.presentation.ui.component

import android.Manifest
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermission() {
    val permission = getPermissionState()
    if(permission.allPermissionsGranted.not()){

    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun getPermissionState(): MultiplePermissionsState {
    val MY_PERMISSION_LIST = listOf(
        Manifest.permission.CAMERA,
    )
    val multiplePermissionsState =
        rememberMultiplePermissionsState(permissions = MY_PERMISSION_LIST) {

        }
    return multiplePermissionsState
}