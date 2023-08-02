package com.bonobono.presentation.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState

@Composable
fun CameraScreen() {
    val cameraState = rememberCameraState()
    var camSelector by rememberCamSelector(CamSelector.Back)
    CameraPreview(
        cameraState = cameraState,
        camSelector = camSelector,
    ) {
        // Camera Preview UI
    }
}