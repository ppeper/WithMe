package com.bonobono.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.bonobono.presentation.R


sealed class MainFab(val route: String, val icon: ImageVector, val title: String) {
    object MISSION : MainFab(NavigationRouteName.MISSION, Icons.Filled.DateRange, FabTitle.MISSION)
    object ENCYCLOPEDIA : MainFab(NavigationRouteName.ENCYCLOPEDIA, Icons.Filled.List, FabTitle.ENCYCLOPEDIA)
    object NOTICE : MainFab(NavigationRouteName.NOTICE, Icons.Filled.Notifications, FabTitle.NOTICE)
}

sealed class CommunityFab(val route: String, val icon: Int, val title: String) {
    object FREE : CommunityFab(NavigationRouteName.COMMUNITY_FREE, R.drawable.ic_write, FabTitle.FREE)
    object WITH : CommunityFab(NavigationRouteName.COMMUNITY_WITH, R.drawable.ic_write, FabTitle.WITH)
    object REPORT : CommunityFab(NavigationRouteName.COMMUNITY_REPORT,  R.drawable.ic_report, FabTitle.REPORT)
}

object FabTitle {
    const val MISSION = "미션"
    const val ENCYCLOPEDIA = "도감"
    const val NOTICE = "공지"

    const val FREE = "자유"
    const val WITH = "함께"
    const val REPORT = "신고"
}