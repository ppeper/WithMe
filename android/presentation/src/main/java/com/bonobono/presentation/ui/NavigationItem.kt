package com.bonobono.presentation.ui

import androidx.annotation.DrawableRes
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_FREE
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_REPORT
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_WITH
import com.bonobono.presentation.ui.NavigationRouteName.DEEP_LINK_SCHEME
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_CHATTING
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_COMMUNITY
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_HOME
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_MAP
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_MY_PAGE

sealed class MainNav(
    override val route: String,
    @DrawableRes val icon: Int,
    override val title: String) : Destination
{
    object Home : MainNav(MAIN_HOME, R.drawable.ic_home, NavigationTitle.MAIN_HOME)
    object Map : MainNav(MAIN_MAP, R.drawable.ic_map, NavigationTitle.MAIN_MAP)
    object Community : MainNav(MAIN_COMMUNITY, R.drawable.ic_community, NavigationTitle.MAIN_COMMUNITY)
    object Chatting : MainNav(MAIN_CHATTING, R.drawable.ic_chat, NavigationTitle.MAIN_CHATTING)
    object MyPage : MainNav(MAIN_MY_PAGE, R.drawable.ic_profile, NavigationTitle.MAIN_MY_PAGE)

    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )

    companion object {
        fun isMainRoute(route: String?): Boolean {
            return when (route) {
                MAIN_HOME, MAIN_MAP, MAIN_CHATTING, MAIN_COMMUNITY, MAIN_MY_PAGE,
                COMMUNITY_REPORT, COMMUNITY_WITH, COMMUNITY_FREE-> true
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

object CommunityFreeNav : Destination {
    override val route: String = NavigationRouteName.COMMUNITY_FREE
    override val title: String = NavigationTitle.COMMUNITY_FREE
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${NoticeNav.route}" }
    )
}

object CommunityWithNav : Destination {
    override val route: String = NavigationRouteName.COMMUNITY_WITH
    override val title: String = NavigationTitle.COMMUNITY_WITH
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${NoticeNav.route}" }
    )
}

object CommunityReportNav : Destination {
    override val route: String = NavigationRouteName.COMMUNITY_REPORT
    override val title: String = NavigationTitle.COMMUNITY_REPORT
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${NoticeNav.route}" }
    )
}

object CameraNav : Destination {
    override val route: String = NavigationRouteName.CAMERA
    override val title: String = NavigationTitle.CAMERA
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${NoticeNav.route}" }
    )
}

object SettingNav : Destination {
    override val route: String = NavigationRouteName.SETTING
    override val title: String = NavigationTitle.SETTING
    override val deepLinks: List<NavDeepLink>
        get() = TODO("Not yet implemented")
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

    // Community
    const val COMMUNITY_FREE = "free_community"
    const val COMMUNITY_WITH = "with_community"
    const val COMMUNITY_REPORT = "report_community"
    // FAB Route
    const val COMMUNITY_POST = "write"
    const val COMMUNITY_POST_REPORT = "report"
    // Gallery Route
    const val GALLERY = "gallery"

    const val CAMERA = "camera"

    const val SETTING = "setting"
    const val PROFILE_EDIT = "profile_edit"
    const val POINT_STORE = "point_store"
}

object NavigationTitle {
    const val MAIN_HOME = "홈"
    const val MAIN_MAP = "지도"
    const val MAIN_CHATTING = "채팅"
    const val MAIN_COMMUNITY = "게시판"
    const val MAIN_MY_PAGE = "프로필"

    const val COMMUNITY_FREE = "자유게시판"
    const val COMMUNITY_WITH = "함께게시판"
    const val COMMUNITY_REPORT = "신고게시판"

    const val MISSION = "미션"
    const val ENCYCLOPEDIA = "도감"
    const val NOTICE = "공지"

    const val CAMERA = "카메라"

    const val SETTING = "설정"
    const val PROFILE_EDIT = "회원정보 수정"
    const val POINT_STORE = "포인트 상점"
}