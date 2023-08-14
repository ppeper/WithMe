package com.bonobono.presentation.ui.common.topbar.utils

import com.bonobono.presentation.ui.BoardDetailNav
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.screen.BoardDetailScreen
import com.bonobono.presentation.ui.common.topbar.screen.ChattingScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityFreeScreen
import com.bonobono.presentation.ui.common.topbar.screen.MissionScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityListScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityReportScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityWithScreen
import com.bonobono.presentation.ui.common.topbar.screen.FindIDScreen
import com.bonobono.presentation.ui.common.topbar.screen.FindPasswordScreen
import com.bonobono.presentation.ui.common.topbar.screen.EncyclopediaScreen
import com.bonobono.presentation.ui.common.topbar.screen.JoinScreen
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsRoute
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsScreen
import com.bonobono.presentation.ui.common.topbar.screen.PointStoreScreen
import com.bonobono.presentation.ui.common.topbar.screen.ProfileEditScreen
import com.bonobono.presentation.ui.common.topbar.screen.Screen
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen

// TopBar 보여줄 요소에 따라 Route 지정
fun getScreen(route: String?): Screen? = when (route) {
    NavigationRouteName.MAIN_COMMUNITY -> CommunityListScreen
    NavigationRouteName.SETTING -> SettingScreen
    NavigationRouteName.COMMUNITY_FREE -> CommunityFreeScreen
    NavigationRouteName.COMMUNITY_WITH -> CommunityWithScreen
    NavigationRouteName.COMMUNITY_REPORT -> CommunityReportScreen
    NavigationRouteName.POINT_STORE -> PointStoreScreen
    NavigationRouteName.PROFILE_EDIT -> ProfileEditScreen
    NavigationRouteName.FIND_ID -> FindIDScreen
    NavigationRouteName.FIND_PASSWORD -> FindPasswordScreen
    BoardDetailNav.route -> BoardDetailScreen
    NavigationRouteName.MISSION -> MissionScreen
    NavigationRouteName.ENCYCLOPEDIA -> EncyclopediaScreen
    NavigationRouteName.JOIN -> JoinScreen
    NavigationRouteName.MAIN_CHATTING -> ChattingScreen
    ManyOptionsRoute -> ManyOptionsScreen
    else -> null
}