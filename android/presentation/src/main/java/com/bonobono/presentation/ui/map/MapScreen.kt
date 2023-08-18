package com.bonobono.presentation.ui.map

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonobono.domain.model.map.Location
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.ARMapNav
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.CampaignCard
import com.bonobono.presentation.ui.main.component.RankingCard
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.MapViewModel
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
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private const val TAG = "MapScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMapScreen(navController: NavHostController, mapViewModel: MapViewModel = hiltViewModel()) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    var selectedChipIndex by remember { mutableStateOf(0) }

    val locations by mapViewModel.locations.collectAsState()
    val selectedIdx = remember {
        mutableStateOf(4)
    }

    LaunchedEffect(Unit) {
        mapViewModel.getLocations()
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 36.dp,
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
                    if (locations.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp), horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = locations[selectedIdx.value].name,
                                style = CustomTextStyle.mapTitleTextStyle
                            )
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
                            Spacer(modifier = Modifier.weight(1f))
                            ElevatedFilterChip(
                                modifier = Modifier.padding(4.dp),
                                selected = false,
                                colors = FilterChipDefaults.elevatedFilterChipColors(
                                    containerColor = PrimaryBlue
                                ),
                                onClick = {
                                    mapViewModel.location.value = locations[selectedIdx.value]
                                    navController.navigate(ARMapNav.route)
                                },
                                label = {
                                    Text(
                                        text = "AR",
                                        style = CustomTextStyle.gameGuideTextStyle.copy(color = White)
                                    )
                                })
                        }
                        if (selectedChipIndex == 0) {
                            BottomSheetCampaignContent(mapViewModel, locations, selectedIdx, navController)
                        } else {
                            BottomSheetRankingContent(mapViewModel, locations, selectedIdx)
                        }
                    }
                }
            }
        }) { innerPadding ->

            MapScreen(navController = navController, scaffoldState, locations, selectedIdx)

    }
}


@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavHostController,
    scaffoldState: BottomSheetScaffoldState,
    locations: List<Location>,
    selectedIdx: MutableState<Int>
) {


    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(35.9078, 127.7669), 6.0)
    }


    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true, isLogoClickEnabled = false)
        )
    }

    val locationSource = rememberFusedLocation()

    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            locationSource = locationSource
        ) {
            MapMarkers(
                locations = locations,
                scaffoldState = scaffoldState,
                cameraPositionState = cameraPositionState,
                selectedIdx = selectedIdx
            )
        }

        MapChips(
            locations = locations,
            cameraPositionState = cameraPositionState,
            selectedIdx = selectedIdx
        )
    }
}

@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapMarkers(
    locations: List<Location>,
    scaffoldState: BottomSheetScaffoldState,
    cameraPositionState: CameraPositionState,
    selectedIdx: MutableState<Int>
) {
    val scope = rememberCoroutineScope()
    locations.forEachIndexed { idx, item ->
        Marker(
            state = MarkerState(position = LatLng(item.centerLatitude, item.centerLongitude)),
            icon = OverlayImage.fromResource(R.drawable.ic_blue_marker),
            width = 48.dp,
            height = 48.dp,
            onClick = {
                selectedIdx.value = idx
                cameraPositionState.position =
                    CameraPosition(LatLng(item.centerLatitude, item.centerLongitude), 11.0)
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
fun MapChips(
    locations: List<Location>,
    selectedIdx: MutableState<Int>,
    cameraPositionState: CameraPositionState
) {
    LazyRow() {
        items(locations.size) { idx ->
            val item = locations[idx]
            ElevatedFilterChip(
                modifier = Modifier.padding(4.dp),
                selected = false,
                elevation = FilterChipDefaults.elevatedFilterChipElevation(1.dp),
                onClick = {
                    selectedIdx.value = idx
                    cameraPositionState.position =
                        CameraPosition(LatLng(item.centerLatitude, item.centerLongitude), 11.0)
                },
                label = {
                    Text(
                        text = item.name,
                        style = CustomTextStyle.gameGuideTextStyle
                    )
                })
        }
    }
}

@Composable
fun BottomSheetRankingContent(
    mapViewModel: MapViewModel,
    locations: List<Location>,
    selectedIdx: MutableState<Int>
) {
    mapViewModel.getRanking(locations[selectedIdx.value].id)
    val rankingList by mapViewModel.ranking.collectAsState()
    LazyColumn() {
        var ranking = 1
        items(rankingList.sortedByDescending { it.count }) {
            RankingCard(
                profileImage = it.profileImg,
                nickName = it.nickname,
                count = it.count,
                ranking = ranking++
            )
        }
    }
}

@Composable
fun BottomSheetCampaignContent(
    mapViewModel: MapViewModel,
    locations: List<Location>,
    selectedIdx: MutableState<Int>,
    navController: NavHostController
) {
    mapViewModel.getCampaign(locations[selectedIdx.value].id)
    val campaignList by mapViewModel.campaign.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "진행중", style = CustomTextStyle.mapTitleTextStyle)
        if(campaignList.filter { !it.completionStatus }.isNullOrEmpty()) {
            NoDataView(title = "진행중인 캠페인")
        } else {
            LazyRow() {
                items(campaignList.filter { !it.completionStatus }) {
                    CampaignCard(modifier = Modifier
                        .zIndex(1f)
                        .clickable {
                            val encodedUrl =
                                URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                            navController.navigate("${NavigationRouteName.LINK_WEB_VIEW}/${encodedUrl}")
                            Log.d(TAG, "BottomSheetCampaignContent: $encodedUrl")
                        }, campaign = it)
                }
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "완료", style = CustomTextStyle.mapTitleTextStyle)
        if(campaignList.filter { it.completionStatus }.isNullOrEmpty()) {
            NoDataView(title = "완료된 캠페인")
        } else {
            LazyRow() {
                items(campaignList.filter { it.completionStatus }) {
                    CampaignCard(campaign = it)
                }
            }
        }
    }
}

@Composable
fun NoDataView(title: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = "없음", tint = TextGray)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "${title}이 없습니다.", style = CustomTextStyle.mapGrayTextStyle)
    }

}

@Preview
@Composable
fun MapPreview() {

}