package com.bonobono.presentation.ui.common.topbar.utils

import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.screen.CommunityListScreen
import com.bonobono.presentation.ui.common.topbar.screen.FindIDScreen
import com.bonobono.presentation.ui.common.topbar.screen.FindPasswordScreen
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsRoute
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsScreen
import com.bonobono.presentation.ui.common.topbar.screen.PointStoreScreen
import com.bonobono.presentation.ui.common.topbar.screen.PostItemScreen
import com.bonobono.presentation.ui.common.topbar.screen.ProfileEditScreen
import com.bonobono.presentation.ui.common.topbar.screen.Screen
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen

// TopBar 보여줄 요소에 따라 Route 지정
fun getScreen(route: String?): Screen? = when (route) {
    NavigationRouteName.MAIN_COMMUNITY -> CommunityListScreen
    NavigationRouteName.COMMUNITY_FREE -> PostItemScreen
    NavigationRouteName.COMMUNITY_WITH -> PostItemScreen
    NavigationRouteName.COMMUNITY_REPORT -> PostItemScreen
    NavigationRouteName.SETTING -> SettingScreen
    NavigationRouteName.POINT_STORE -> PointStoreScreen
    NavigationRouteName.PROFILE_EDIT -> ProfileEditScreen
    NavigationRouteName.FIND_ID -> FindIDScreen
    NavigationRouteName.FIND_PASSWORD -> FindPasswordScreen
    ManyOptionsRoute -> ManyOptionsScreen
    else -> null
}