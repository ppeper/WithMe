package com.bonobono.presentation.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.CatchKey
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.MainActivity
import com.bonobono.presentation.utils.characterList
import com.bonobono.presentation.viewmodel.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

private const val TAG = "ARMapScreen"
@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ARMapScreen(
    navController: NavHostController,
    mapViewModel: MapViewModel
) {
    val curLocation = mapViewModel.location.value

    val catchCharacters by mapViewModel.catchCharacters.collectAsState()
    mapViewModel.getCatchCharacters(CatchKey(curLocation.name, 1))

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(curLocation.centerLatitude, curLocation.centerLongitude),  15.0)
    }

    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true, isLogoClickEnabled = false)
        )
    }

    val locationSource = rememberFusedLocation()

    val context = LocalContext.current as MainActivity
    // 10초마다 위치 업데이트를 처리합니다.
    val locationUpdateScope = rememberCoroutineScope()
    var currentLocation: MutableState<Location?> = remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        val locationUpdateJob = locationUpdateScope.launch {
            while (isActive) {
                // 위치 정보를 얻어오는 로직을 구현합니다. (예: FusedLocationProviderClient 사용)
                getLocation(context, currentLocation)
                delay(2000) // 10초 대기
            }
        }
    }


    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            locationSource = locationSource
        ) {
            CharacterMarkers(
                characters = catchCharacters,
                cameraPositionState = cameraPositionState
            )
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun CharacterMarkers(
    characters: List<CatchCharacter>,
    cameraPositionState: CameraPositionState,
) {
    characters.forEachIndexed { idx, item ->
        val getLocalCharacter = characterList.find { it.name == item.ourCharacter.name }
        val icon = getLocalCharacter?.icon ?: R.drawable.character_beluga
        Marker(
            state = MarkerState(position = LatLng(item.charLatitude, item.charLongtitude)),
            icon = OverlayImage.fromResource(icon),
            width = 48.dp,
            height = 48.dp,
            onClick = {
                cameraPositionState.position =
                    CameraPosition(LatLng(item.charLatitude, item.charLongtitude), 11.0)
                true
            }
        )
    }
}

@SuppressLint("MissingPermission")
fun getLocation(context: Context, currentLocation: MutableState<Location?>) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            // 위치 정보를 성공적으로 얻었을 때 콜백으로 전달
            Log.d(TAG, "getLocation: $location")
            currentLocation.value = location

        }
        .addOnFailureListener { exception: Exception ->
            // 위치 정보를 얻어오는데 실패했을 때 예외 처리
        }
}