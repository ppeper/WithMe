package com.bonobono.presentation.ui.common.topbar.screen

import androidx.annotation.DrawableRes
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem

sealed interface Screen {
    val isCenterTopBar: Boolean
    val route: String
    val isAppBarVisible: Boolean
    @get:DrawableRes
    val navigationIcon: Int?
    val navigationIconContentDescription: String?
    val onNavigationIconClick: (() -> Unit)?
    val title: String
    val actions: List<ActionMenuItem>
}