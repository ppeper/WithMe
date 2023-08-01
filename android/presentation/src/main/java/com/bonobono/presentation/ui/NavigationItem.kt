package com.bonobono.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.bonobono.presentation.ui.NavigationRouteName.DEEP_LINK_SCHEME
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_CHATTING
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_COMMUNITY
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_HOME
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_MAP
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_MY_PAGE

sealed class MainNav(
    override val route: String,
    val icon: ImageVector,
    override val title: String
) : Destination {
    object Home : MainNav(MAIN_HOME, Icons.Filled.Home, NavigationTitle.MAIN_HOME)
    object Map : MainNav(MAIN_MAP, Icons.Filled.Favorite, NavigationTitle.MAIN_MAP)
    object Community : MainNav(MAIN_COMMUNITY, Icons.Filled.Star, NavigationTitle.MAIN_COMMUNITY)
    object Chatting : MainNav(MAIN_CHATTING, Icons.Filled.Favorite, NavigationTitle.MAIN_CHATTING)
    object MyPage : MainNav(MAIN_MY_PAGE, Icons.Filled.AccountBox, NavigationTitle.MAIN_MY_PAGE)

    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )

    companion object {
        fun isMainRoute(route: String?): Boolean {
            return when (route) {
                MAIN_HOME, MAIN_MAP, MAIN_CHATTING, MAIN_COMMUNITY, MAIN_MY_PAGE -> true
                else -> false
            }
        }
    }
}

object MissionNav : Destination {
    override val route: String = NavigationRouteName.MISSION
    override val title: String = NavigationTitle.MISSION
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )
}

object EncyclopediaNav : Destination {
    override val route: String = NavigationRouteName.ENCYCLOPEDIA
    override val title: String = NavigationTitle.ENCYCLOPEDIA
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )
}

object NoticeNav : Destination {
    override val route: String = NavigationRouteName.NOTICE
    override val title: String = NavigationTitle.NOTICE
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )
}

object CameraNav : Destination {
    override val route: String = NavigationRouteName.CAMERA
    override val title: String = NavigationTitle.CAMERA
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${NoticeNav.route}" }
    )
}


interface Destination {
    val route: String
    val title: String
    val deepLinks: List<NavDeepLink>
}

object NavigationRouteName {
    const val DEEP_LINK_SCHEME = "bonobono://"
    const val MAIN_HOME = "main_home"
    const val MAIN_COMMUNITY = "main_community"
    const val MAIN_MY_PAGE = "main_my_page"
    const val MAIN_CHATTING = "main_chat"
    const val MAIN_MAP = "main_map"

    const val MISSION = "mission"
    const val ENCYCLOPEDIA = "encyclopedia"
    const val NOTICE = "notice"

    const val CAMERA = "camera"
}

object NavigationTitle {
    const val MAIN_HOME = "홈"
    const val MAIN_MAP = "지도"
    const val MAIN_CHATTING = "채팅"
    const val MAIN_COMMUNITY = "커뮤니티"
    const val MAIN_MY_PAGE = "마이페이지"

    const val MISSION = "미션"
    const val ENCYCLOPEDIA = "도감"
    const val NOTICE = "공지"

    const val CAMERA = "카메라"
}