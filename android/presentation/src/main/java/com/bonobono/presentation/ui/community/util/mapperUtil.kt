package com.bonobono.presentation.ui.community.util

import androidx.navigation.NavController
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.NavigationRouteName

fun textMapper(
    navController: NavController
): String {
    val route = navController.currentDestination?.route
    return when (route) {
        CommunityFab.FREE.route -> { "자유롭게 글을 작성해주세요" }
        CommunityFab.WITH.route -> { "함께하고 싶은 내용을 작성해주세요" }
        CommunityFab.REPORT.route -> { "신고할 내용을 작성해 주세요\n\n(필수) 신고할 해변과 위치를 지도에서 선택해주세요!" }
        else -> { "자유롭게 글을 작성해주세요" }
    }
}

fun routeMapper(
    navController: NavController
): String {
    val route = navController.currentDestination?.route
    return when (route) {
        CommunityFab.FREE.route -> { NavigationRouteName.GALLERY }
        CommunityFab.WITH.route -> { NavigationRouteName.GALLERY_WITH }
        CommunityFab.REPORT.route -> { NavigationRouteName.GALLERY_REPORT }
        (route?.startsWith(NavigationRouteName.COMMUNITY_UPDATE)).toString() -> { NavigationRouteName.GALLERY_UPDATE }
        (route?.startsWith(NavigationRouteName.COMMUNITY_UPDATE_WITH)).toString() -> { NavigationRouteName.GALLERY_UPDATE_WITH }
        (route?.startsWith(NavigationRouteName.COMMUNITY_UPDATE_REPORT)).toString() -> { NavigationRouteName.GALLERY_UPDATE_REPORT }
        else -> { NavigationRouteName.GALLERY }
    }
}

