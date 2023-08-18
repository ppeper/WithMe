package com.bonobono.presentation.ui.community.views.map

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.TextGray
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.overlay.OverlayImage

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun ReportMapAndLocation(
    modifier: Modifier = Modifier,
    mapState: LatLng,
    locationName: String,
) {
    val markerState = MarkerState().apply { position = mapState }
    val cameraPositionState = CameraPositionState().apply {
        position = CameraPosition(mapState, MIN_ZOOM)
    }
    val mapUiSettings = MapUiSettings(
        isLocationButtonEnabled = false,
        isZoomControlEnabled = false,
        isLogoClickEnabled = false,
    )

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "해변 위치: $locationName",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    contentDescription = "위치",
                    tint = DarkGray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = DarkGray,
                unfocusedBorderColor = Black_100
            ),
            textStyle = TextStyle(fontSize = 14.sp, color = Black_100),
            readOnly = true,
            enabled = false,
            shape = RoundedCornerShape(6.dp)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(
                    width = 2.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            NaverMap(
                modifier = modifier.clip(RoundedCornerShape(10.dp)),
                cameraPositionState = cameraPositionState,
                uiSettings = mapUiSettings
            ) {
                Marker(
                    state = markerState,
                    icon = OverlayImage.fromResource(R.drawable.ic_marker_trash),
                )
            }
        }
    }
}