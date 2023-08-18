package com.bonobono.presentation.ui.common.topbar.screen

import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ChattingScreen : Screen {

    enum class AppBarIcons {
        Search
    }

    private val _buttons = MutableSharedFlow<AppBarIcons>(extraBufferCapacity = 1)
    val buttons: Flow<AppBarIcons> = _buttons.asSharedFlow()

    override val isCenterTopBar: Boolean = false
    override val route: String = NavigationRouteName.MAIN_COMMUNITY
    override val isAppBarVisible: Boolean = true
    override val navigationIcon: Int? = null
    override val navigationIconContentDescription: String? = null
    override val onNavigationIconClick: (() -> Unit)? = null
    override val title: String = "채팅목록"
    override val actions: List<ActionMenuItem> = listOf(
        ActionMenuItem.IconMenuItem.AlwaysShown(
            title = "검색",
            onClick = {
                _buttons.tryEmit(AppBarIcons.Search)
            },
            icon = R.drawable.ic_search,
            contentDescription = "검색"
            )
    )

}