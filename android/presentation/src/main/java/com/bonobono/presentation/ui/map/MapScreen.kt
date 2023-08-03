package com.bonobono.presentation.ui.map

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bonobono.presentation.ui.CameraNav
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.utils.NavigationUtils
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainMapScreen(navController: NavHostController) {
    val latLng = LatLng(37.7387295, 127.0458908)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 15f)
    }
    val permissions = listOf<String>(
        android.Manifest.permission.CAMERA
    )
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions = permissions)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = latLng),
            title = "의정부역",
            snippet = "Uijeongbu subway"
        )
    }
    // 버튼 추가
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Button(
            onClick = {
                multiplePermissionsState.launchMultiplePermissionRequest()
                // 버튼을 눌렀을 때, 카메라 포지션을 업데이트하여 원하는 장소로 이동
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(LatLng(37.7, 127.1), 15f)
                NavigationUtils.navigate(
                    navController, CameraNav.route,
                    navController.graph.startDestinationRoute
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Text("AR")
        }
    }
}
