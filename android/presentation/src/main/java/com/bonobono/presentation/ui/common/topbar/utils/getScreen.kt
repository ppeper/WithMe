package com.bonobono.presentation.ui.common.topbar.utils

import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsRoute
import com.bonobono.presentation.ui.common.topbar.screen.ManyOptionsScreen
import com.bonobono.presentation.ui.common.topbar.screen.Screen

// TopBar 보여줄 요소에 따라 Route 지정
fun getScreen(route: String?): Screen? = when (route) {
    ManyOptionsRoute -> ManyOptionsScreen
    else -> null
}