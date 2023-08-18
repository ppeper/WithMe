package com.bonobono.presentation.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.CatchKey
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.CameraNav
import com.bonobono.presentation.ui.MainActivity
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.GamePromptBox
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
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
import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.sqrt

private const val TAG = "ARMapScreen"

@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ARMapScreen(
    navController: NavHostController, mapViewModel: MapViewModel
) {
    var curLocation = mapViewModel.location.value

    val catchCharacters by mapViewModel.catchCharacters.collectAsState()


    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position =
            CameraPosition(LatLng(curLocation.centerLatitude, curLocation.centerLongitude), 15.0)
    }

    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true, isLogoClickEnabled = false)
        )
    }

    val locationSource = rememberFusedLocation()
    var switchCheckedState by remember { mutableStateOf(false) }
    val context = LocalContext.current as MainActivity
    // 10초마다 위치 업데이트를 처리합니다.
    val locationUpdateScope = rememberCoroutineScope()
    var currentLocation: MutableState<Location?> = remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        mapViewModel.getCatchCharacters(CatchKey(curLocation.name, 1))
    }

    if(switchCheckedState) {
        LaunchedEffect(Unit) {
            val locationUpdateJob = locationUpdateScope.launch {
                Log.d(TAG, "ARMapScreen: $switchCheckedState")
                while (isActive) {
                    if(!switchCheckedState) {
                        return@launch
                    }
                    getLocation(context, currentLocation)
                    // 위치 정보를 얻어오는 로직을 구현합니다. (예: FusedLocationProviderClient 사용)
                    if (currentLocation.value != null) {
                        val curCatchCharacter =
                            findCharacterInRadius(catchCharacters, currentLocation.value!!, 1000.0)
                        if (curCatchCharacter != null) {
                            mapViewModel.setCurCatchCharacter(catchCharacter = curCatchCharacter)
                            Log.d(TAG, "juyong: ${mapViewModel.curCatchCharacter.value}")
                            navController.navigate(CameraNav.route)
                        }
                    }
                    delay(2000) // 10초 대기
                }
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
                characters = catchCharacters, cameraPositionState = cameraPositionState
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            GamePromptBox(
                name = "클로버 요정", content = "지도에 보이는 위치로 가서 저주에 걸린 동물들을 구해줘!",
                modifier = Modifier
            )

            Column (
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Switch(
                    checked = switchCheckedState,
                    onCheckedChange = { switchCheckedState = it },
                    colors = SwitchDefaults.colors(uncheckedTrackColor = White),
                )
                Text(text = "50m 이내 감지", style = CustomTextStyle.quizContentStyle)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipAR(navController: NavHostController) {
    ElevatedFilterChip(modifier = Modifier.padding(4.dp),
        colors = FilterChipDefaults.elevatedFilterChipColors(
            containerColor = PrimaryBlue
        ),
        selected = false,
        onClick = {
            navController.navigate(CameraNav.route)
        },
        label = {
            Text(
                text = "AR", style = CustomTextStyle.gameGuideTextStyle, color = White
            )
        })
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun CharacterMarkers(
    characters: List<CatchCharacter>,
    cameraPositionState: CameraPositionState,
) {
    characters.forEachIndexed { idx, item ->
        val getLocalCharacter = characterList.find { it.name == item.ourCharacter.name }
        val icon = getLocalCharacter?.icon ?: R.drawable.ic_beluga
        Marker(state = MarkerState(position = LatLng(item.charLatitude, item.charLongtitude)),
            icon = OverlayImage.fromResource(R.drawable.ic_pixel_icon),
            width = 32.dp,
            height = 32.dp,
            onClick = {
                cameraPositionState.position =
                    CameraPosition(LatLng(item.charLatitude, item.charLongtitude), 11.0)
                true
            })
    }
}

@SuppressLint("MissingPermission")
fun getLocation(context: Context, currentLocation: MutableState<Location?>) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        // 위치 정보를 성공적으로 얻었을 때 콜백으로 전달
        currentLocation.value = location
        Log.d(TAG, "getLocation: $location")
    }.addOnFailureListener { exception: Exception ->
        // 위치 정보를 얻어오는데 실패했을 때 예외 처리
    }
}

fun findCharacterInRadius(
    characterList: List<CatchCharacter>, location: Location, radiusMeters: Double
): CatchCharacter? {
    val targetLatLng = LatLng(location.latitude, location.longitude)
    for (character in characterList) {
        val characterLatLng = LatLng(character.charLatitude, character.charLongtitude)
        val distance = computeDistanceBetween(targetLatLng, characterLatLng)
        Log.d(TAG, "findCharacterInRadius: $distance")
        if (distance <= radiusMeters) {
            return character
        }
    }
    return null
}

fun computeDistanceBetween(latLng1: LatLng, latLng2: LatLng): Double {
    val earthRadius = 6371000.0 // Earth's radius in meters
    val dLat = Math.toRadians(latLng2.latitude - latLng1.latitude)
    val dLng = Math.toRadians(latLng2.longitude - latLng1.longitude)
    val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(latLng1.latitude)) * cos(
        Math.toRadians(latLng2.latitude)
    ) * sin(dLng / 2) * sin(dLng / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c
}