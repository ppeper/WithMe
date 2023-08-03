package com.bonobono.presentation.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bonobono.domain.model.map.Location
import com.bonobono.presentation.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMapScreen(navController: NavHostController) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 60.dp,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Swipe up to expand sheet")
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            MapScreen(navController = navController, scaffoldState)
        }
    }
}


@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavHostController, scaffoldState: BottomSheetScaffoldState) {
    val scope = rememberCoroutineScope()
    val locations = listOf<Location>(
        Location(1, "부산 광안리", 35.153387, 129.113506),
        Location(2, "충남 서천 춘장대 해수욕장", 36.016374, 126.700953),
        Location(3, "울산 일산 해수욕장", 35.431840, 129.371506),
        Location(4, "여수 웅천천수공원", 34.746716, 127.73199)
    )
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(35.9078, 127.7669), 6.0)
    }

    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true)
        )
    }

    Box(Modifier.fillMaxSize()) {
        NaverMap(cameraPositionState = cameraPositionState, uiSettings = mapUiSettings) {
            locations.forEach { item ->
                Marker(
                    state = MarkerState(position = LatLng(item.latitude, item.longitude)),
                    icon = OverlayImage.fromResource(R.drawable.ic_beach_pin),
                    width = 48.dp,
                    height = 48.dp,
                    onClick = {
                        cameraPositionState.position =
                            CameraPosition(LatLng(item.latitude, item.longitude), 11.0)
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                        true
                    }
                )
            }
        }
        Column {
            LazyRow() {
                items(locations) { item ->
                    //Chip()
                }
            }
        }
    }
}

@Composable
fun BottomSheetRankingContent(cameraPositionState: CameraPositionState, location: Location) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}

@Composable
fun BottomSheetCampaignContent(cameraPositionState: CameraPositionState, location: Location) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(location: Location, icon: @Composable () -> Unit, onClick: () -> Unit) {
    ElevatedFilterChip(
        leadingIcon = { icon },
        modifier = Modifier.padding(4.dp),
        selected = false,
        onClick = { onClick() },
        label = { Text(text = location.locationName) })
}

@Preview
@Composable
fun MapPreview() {

}