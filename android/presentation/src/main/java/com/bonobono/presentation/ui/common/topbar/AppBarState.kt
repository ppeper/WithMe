package com.bonobono.presentation.ui.common.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bonobono.presentation.ui.BoardDetailNav
import com.bonobono.presentation.ui.common.topbar.item.ActionMenuItem
import com.bonobono.presentation.ui.common.topbar.screen.Screen
import com.bonobono.presentation.ui.common.topbar.utils.getScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// Route에 따라 TopBar 요소를 바꾸기 위한 State
@Stable
class AppBarState(
    navController: NavController,
    scope: CoroutineScope,
) {
    init {
        navController.currentBackStackEntryFlow
            .distinctUntilChanged()
            .onEach { backStackEntry ->
                // 4
                val route = backStackEntry.destination.route
                currentScreen = if (route?.contains(BoardDetailNav.route) == true) getScreen(BoardDetailNav.route) else getScreen(route)
            }
            .launchIn(scope)
    }

    var currentScreen by mutableStateOf<Screen?>(null)
        private set

    val isCenterTopBar: Boolean
        @Composable get() = currentScreen?.isCenterTopBar == true
    val isVisible: Boolean
        @Composable get() = currentScreen?.isAppBarVisible == true

    val navigationIcon: Int?
        @Composable get() = currentScreen?.navigationIcon

    val navigationIconContentDescription: String?
        @Composable get() = currentScreen?.navigationIconContentDescription

    val onNavigationIconClick: (() -> Unit)?
        @Composable get() = currentScreen?.onNavigationIconClick

    val title: String
        @Composable get() = currentScreen?.title.orEmpty()

    val actions: List<ActionMenuItem>
        @Composable get() = currentScreen?.actions.orEmpty()
}

@Composable
fun rememberAppBarState(
    navController: NavController,
    scope: CoroutineScope = rememberCoroutineScope(),
) = remember {
    AppBarState(
        navController,
        scope,
    )
}