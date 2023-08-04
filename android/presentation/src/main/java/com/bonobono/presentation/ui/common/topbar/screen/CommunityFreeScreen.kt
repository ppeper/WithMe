package com.bonobono.presentation.ui.common.topbar.screen

import com.bonobono.presentation.R
import com.bonobono.presentation.ui.CommunityFreeNav
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object CommunityFreeScreen: Screen {

    enum class AppBarIcons {
        NavigationIcon,
        Search,
        Alarm
    }

    private val _buttons = MutableSharedFlow<AppBarIcons>(extraBufferCapacity = 1)
    val buttons: Flow<AppBarIcons> = _buttons.asSharedFlow()

    override val isCenterTopBar: Boolean = false
    override val route: String = CommunityFreeNav.route
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: Int = R.drawable.ic_back
    override val navigationIconContentDescription: String = "뒤로가기"
    override val onNavigationIconClick: () -> Unit = {
        _buttons.tryEmit(AppBarIcons.NavigationIcon)
    }
    override val title: String = CommunityFreeNav.title
    override val actions: List<ActionMenuItem> = listOf(
        ActionMenuItem.IconMenuItem.AlwaysShown(
            title = "검색",
            onClick = {
                _buttons.tryEmit(AppBarIcons.Search)
            },
            icon = R.drawable.ic_search,
            contentDescription = "검색"
        ),
        ActionMenuItem.IconMenuItem.AlwaysShown(
            title = "좋아요",
            onClick = {
                _buttons.tryEmit(AppBarIcons.Alarm)
            },
            icon = R.drawable.ic_alarm,
            contentDescription = "좋아요"
        )

    )
}