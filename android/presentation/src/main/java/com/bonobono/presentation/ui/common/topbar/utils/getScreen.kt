package com.bonobono.presentation.ui.common.topbar.utils

import com.bonobono.presentation.ui.BoardDetailNav
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.screen.BoardDetailScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityFreeScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityListScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityReportScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityWithScreen
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsRoute
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsScreen
import com.bonobono.presentation.ui.common.topbar.screen.Screen
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen

// TopBar 보여줄 요소에 따라 Route 지정
fun getScreen(route: String?): Screen? = when (route) {
    NavigationRouteName.MAIN_COMMUNITY -> CommunityListScreen
    NavigationRouteName.COMMUNITY_FREE -> PostItemScreen
    NavigationRouteName.COMMUNITY_WITH -> PostItemScreen
    NavigationRouteName.COMMUNITY_REPORT -> PostItemScreen
    NavigationRouteName.SETTING -> SettingScreen
    NavigationRouteName.COMMUNITY_FREE -> CommunityFreeScreen
    NavigationRouteName.COMMUNITY_WITH -> CommunityWithScreen
    NavigationRouteName.COMMUNITY_REPORT -> CommunityReportScreen
    BoardDetailNav.route -> BoardDetailScreen
    ManyOptionsRoute -> ManyOptionsScreen
    else -> null
}