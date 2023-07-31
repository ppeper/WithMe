package com.bonobono.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


sealed class MainFab(val route: String, val icon: ImageVector, val title: String) {
    object MISSION : MainFab(NavigationRouteName.MISSION, Icons.Filled.DateRange, FabTitle.MISSION)
    object ENCYCLOPEDIA : MainFab(NavigationRouteName.ENCYCLOPEDIA, Icons.Filled.Star, FabTitle.ENCYCLOPEDIA)
    object NOTICE : MainFab(NavigationRouteName.NOTICE, Icons.Filled.Notifications, FabTitle.NOTICE)
}

object FabTitle {
    const val MISSION = "미션"
    const val ENCYCLOPEDIA = "도감"
    const val NOTICE = "공지"
}