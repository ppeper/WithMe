package com.bonobono.presentation.ui.common.topbar.utils

import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.screen.CommunityFreeScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityListScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityReportScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityWithScreen
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsRoute
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsScreen
import com.bonobono.presentation.ui.common.topbar.screen.Screen

// TopBar 보여줄 요소에 따라 Route 지정
fun getScreen(route: String?): Screen? = when (route) {
    NavigationRouteName.MAIN_COMMUNITY -> CommunityListScreen
    NavigationRouteName.COMMUNITY_FREE -> CommunityFreeScreen
    NavigationRouteName.COMMUNITY_WITH -> CommunityWithScreen
    NavigationRouteName.COMMUNITY_REPORT -> CommunityReportScreen
    ManyOptionsRoute -> ManyOptionsScreen
    else -> null
}