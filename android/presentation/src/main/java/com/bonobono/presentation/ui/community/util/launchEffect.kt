package com.bonobono.presentation.ui.community.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.screen.BoardDetailScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityFreeScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityReportScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityWithScreen
import com.bonobono.presentation.viewmodel.NotificationViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun freeLaunchEffect(
    type: String,
    navController: NavController

) {
    LaunchedEffect(key1 = Unit) {
        CommunityFreeScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityFreeScreen.AppBarIcons.Search -> {
                        navController.navigate("${NavigationRouteName.COMMUNITY_SEARCH}/$type")
                    }
                    CommunityFreeScreen.AppBarIcons.Alarm -> {
                        navController.navigate(NavigationRouteName.COMMUNITY_NOTIFICATION)
                    }
                    CommunityFreeScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
}

@Composable
fun withLaunchEffect(
    type: String,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        CommunityWithScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityWithScreen.AppBarIcons.Search -> {
                        navController.navigate("${NavigationRouteName.COMMUNITY_SEARCH}/$type")
                    }

                    CommunityWithScreen.AppBarIcons.Alarm -> {
                        navController.navigate(NavigationRouteName.COMMUNITY_NOTIFICATION)
                    }
                    CommunityWithScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
}
@Composable
fun reportLaunchEffect(
    type: String,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        CommunityReportScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityReportScreen.AppBarIcons.Search -> {
                        navController.navigate("${NavigationRouteName.COMMUNITY_SEARCH}/$type")
                    }

                    CommunityReportScreen.AppBarIcons.Alarm -> {
                        navController.navigate(NavigationRouteName.COMMUNITY_NOTIFICATION)
                    }
                    CommunityReportScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
}

@Composable
fun boardDetailLaunchEffect(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        BoardDetailScreen.buttons
            .onEach { button ->
                when (button) {
                    BoardDetailScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
}