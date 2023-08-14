package com.bonobono.presentation.ui.common.topbar.screen

import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem

object CommunityListScreen : Screen {


    override val isCenterTopBar: Boolean = false
    override val route: String = NavigationRouteName.MAIN_COMMUNITY
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: Int? = null
    override val navigationIconContentDescription: String? = null
    override val onNavigationIconClick: (() -> Unit)? = null
    override val title: String = "게시판"
    override val actions: List<ActionMenuItem> = listOf()
}