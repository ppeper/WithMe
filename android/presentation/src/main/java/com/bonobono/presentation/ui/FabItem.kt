package com.bonobono.presentation.ui

import com.bonobono.presentation.R


sealed class MainFab(val route: String, val icon: Int, val title: String) {
    object MISSION : MainFab(NavigationRouteName.MISSION, R.drawable.ic_mission, FabTitle.MISSION)
    object ENCYCLOPEDIA : MainFab(NavigationRouteName.ENCYCLOPEDIA, R.drawable.ic_ecyclopedia, FabTitle.ENCYCLOPEDIA)
    object NOTICE : MainFab(NavigationRouteName.NOTICE, R.drawable.ic_notice, FabTitle.NOTICE)
}

sealed class CommunityFab(val route: String, val icon: Int, val title: String) {
    object FREE : CommunityFab(NavigationRouteName.COMMUNITY_POST, R.drawable.ic_write, FabTitle.FREE)
    object WITH : CommunityFab(NavigationRouteName.COMMUNITY_POST_WITH, R.drawable.ic_write, FabTitle.WITH)
    object REPORT : CommunityFab(NavigationRouteName.COMMUNITY_POST_REPORT,  R.drawable.ic_report, FabTitle.REPORT)
    object MAP: CommunityFab(NavigationRouteName.POST_CAMPAIGN, R.drawable.ic_write, FabTitle.MAP)
}

object FabTitle {
    const val MISSION = "미션"
    const val ENCYCLOPEDIA = "도감"
    const val NOTICE = "공지"
    const val MAP = "지도"

    const val FREE = "자유"
    const val WITH = "함께"
    const val REPORT = "신고"
}
