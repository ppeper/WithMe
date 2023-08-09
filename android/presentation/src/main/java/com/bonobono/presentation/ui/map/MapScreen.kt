package com.bonobono.presentation.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.Location
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.CameraNav
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.CampaignCard
import com.bonobono.presentation.ui.main.component.RankingCard
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.NavigationUtils
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMapScreen(navController: NavHostController) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    var selectedChipIndex by remember { mutableStateOf(0) }
    var locationTitle = remember { mutableStateOf("") }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 60.dp,
        containerColor = White,
        sheetShadowElevation = 0.dp,
        sheetTonalElevation = 0.dp,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = locationTitle.value, style = CustomTextStyle.mapTitleTextStyle)
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        ElevatedFilterChip(
                            modifier = Modifier.padding(4.dp),
                            selected = false,
                            onClick = {
                                selectedChipIndex = 0
                            },
                            label = {
                                Text(
                                    text = "캠페인",
                                    style = CustomTextStyle.gameGuideTextStyle
                                )
                            })
                        ElevatedFilterChip(
                            modifier = Modifier.padding(4.dp),
                            selected = false,
                            onClick = {
                                selectedChipIndex = 1
                            },
                            label = {
                                Text(
                                    text = "랭킹",
                                    style = CustomTextStyle.gameGuideTextStyle
                                )
                            })
                    }
                    if (selectedChipIndex == 0) {
                        BottomSheetCampaignContent()
                    } else {
                        BottomSheetRankingContent()
                    }
                }
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            MapScreen(navController = navController, scaffoldState, locationTitle)
        }
    }
}


@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavHostController,
    scaffoldState: BottomSheetScaffoldState,
    locationTitle: MutableState<String>
) {
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
            MapUiSettings(isLocationButtonEnabled = true, isLogoClickEnabled = false)
        )
    }

    val locationSource = rememberFusedLocationSource()

    Box(Modifier.fillMaxSize()) {
        NaverMap(cameraPositionState = cameraPositionState, uiSettings = mapUiSettings, locationSource = locationSource) {
            MapMarkers(
                locations = locations,
                scaffoldState = scaffoldState,
                cameraPositionState = cameraPositionState,
                locationTitle = locationTitle
            )

        }
        Column {
            MapChips(locations = locations, cameraPositionState = cameraPositionState, locationName = locationTitle)
            Column(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(4.dp)
            ) {
                ChipAR(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipAR(navController: NavHostController) {
    ElevatedFilterChip(
        modifier = Modifier
            .padding(4.dp),
        colors = FilterChipDefaults.elevatedFilterChipColors(
            containerColor = PrimaryBlue
        ),
        selected = false,
        onClick = { navController.navigate(CameraNav.route) },
        label = {
            Text(
                text = "AR",
                style = CustomTextStyle.gameGuideTextStyle,
                color = White
            )
        })
}

@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapMarkers(
    locations: List<Location>,
    scaffoldState: BottomSheetScaffoldState,
    cameraPositionState: CameraPositionState,
    locationTitle: MutableState<String>
) {
    val scope = rememberCoroutineScope()
    locations.forEach { item ->
        Marker(
            state = MarkerState(position = LatLng(item.latitude, item.longitude)),
            icon = OverlayImage.fromResource(R.drawable.ic_beach_pin),
            width = 48.dp,
            height = 48.dp,
            onClick = {
                locationTitle.value = item.locationName
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapChips(locations: List<Location>, cameraPositionState: CameraPositionState, locationName: MutableState<String>) {
    LazyRow() {
        items(locations) { item ->
            ElevatedFilterChip(
                modifier = Modifier.padding(4.dp),
                selected = false,
                elevation = FilterChipDefaults.elevatedFilterChipElevation(1.dp),
                onClick = {
                    locationName.value = item.locationName
                    cameraPositionState.position =
                        CameraPosition(LatLng(item.latitude, item.longitude), 11.0)
                },
                label = {
                    Text(
                        text = item.locationName,
                        style = CustomTextStyle.gameGuideTextStyle
                    )
                })
        }
    }
}

@Composable
fun BottomSheetRankingContent() {
    val items = listOf<Int>(1, 2, 3)
    LazyColumn() {
        var ranking = 1
        items(items) {
            RankingCard(
                profileImage = R.drawable.beluga_whale,
                nickName = "주용가리",
                count = 3,
                ranking = ranking++
            )
        }
    }
}

@Composable
fun BottomSheetCampaignContent() {
    val items = listOf<Campaign>(
        Campaign(-1, 1, "일산 해수욕장", Date(), Date(), false, "해양수산부"),
        Campaign(-1, 1, "일산 해수욕장", Date(), Date(), false, "해양수산부"),
        Campaign(-1, 1, "일산 해수욕장", Date(), Date(), false, "해양수산부")
    )
    val completed = listOf<Campaign>(
        Campaign(-1, 1, "일산 해수욕장", Date(), Date(), true, "해양수산부"),
        Campaign(-1, 1, "일산 해수욕장", Date(), Date(), true, "해양수산부"),
        Campaign(-1, 1, "일산 해수욕장", Date(), Date(), true, "해양수산부")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 12.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Text(text = "진행중", style = CustomTextStyle.mapTitleTextStyle)
        LazyRow() {
            items(items) {
                CampaignCard(modifier = Modifier.zIndex(1f),campaign = it)
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "완료", style = CustomTextStyle.mapTitleTextStyle)
        LazyRow() {
            items(completed) {
                CampaignCard(campaign = it)
            }
        }
    }
}

@Preview
@Composable
fun MapPreview() {

}