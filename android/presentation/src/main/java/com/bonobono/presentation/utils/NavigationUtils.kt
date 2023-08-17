package com.bonobono.presentation.utils

import androidx.navigation.NavHostController
import com.bonobono.presentation.ui.Destination
import com.bonobono.presentation.ui.MainNav
import com.bonobono.presentation.ui.NavigationRouteName

object NavigationUtils {

    fun navigate(
        controller: NavHostController,
        routeName: String,
        backStackRouteName: String? =null,
        isLaunchSingleTop: Boolean= true,
        needToRestoreState: Boolean= true
    ) {
        controller.navigate(routeName) {
            if(backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true}
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun findDestination(route : String?) : Destination {
        return when(route) {
            NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
//            NavigationRouteName.MAIN_CHATTING -> MainNav.Chatting
            NavigationRouteName.MAIN_HOME -> MainNav.Home
            NavigationRouteName.MAIN_MAP -> MainNav.Map
            NavigationRouteName.MAIN_COMMUNITY -> MainNav.Community

            else -> MainNav.Home
        }
    }
}