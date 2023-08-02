package com.bonobono.presentation.ui.common.topbar.screen

import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MissionScreen : Screen {

    enum class AppBarIcons {
        NavigationIcon,
    }

    private val _buttons = MutableSharedFlow<AppBarIcons>(extraBufferCapacity = 1)
    val buttons: Flow<AppBarIcons> = _buttons.asSharedFlow()

    override val isCenterTopBar: Boolean = true
    override val route: String = NavigationRouteName.MAIN_COMMUNITY
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: Int? = R.drawable.ic_back
    override val navigationIconContentDescription: String = "뒤로가기"
    override val onNavigationIconClick: () -> Unit = {
        _buttons.tryEmit(AppBarIcons.NavigationIcon)
    }
    override val title: String = "미션"
    override val actions: List<ActionMenuItem> = listOf()

}