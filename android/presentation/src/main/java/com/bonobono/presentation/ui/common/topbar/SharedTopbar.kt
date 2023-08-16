package com.bonobono.presentation.ui.common.topbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonobono.presentation.ui.common.topbar.item.ActionsMenu
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.NotificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTopAppBar(
    appBarState: AppBarState,
    modifier: Modifier = Modifier,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    val notificationList = notificationViewModel.notificationList.collectAsStateWithLifecycle()
    Log.d("TEST", "SharedTopAppBar: ${notificationList.value.size}")
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    if (appBarState.isCenterTopBar) {
        CenterAlignedTopAppBar(
            modifier = modifier
                .background(color = White)
                .graphicsLayer {
                    shadowElevation = 10f
                },
            navigationIcon = {
                val icon = appBarState.navigationIcon
                val callback = appBarState.onNavigationIconClick
                if (icon != null) {
                    IconButton(onClick = { callback?.invoke() }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = appBarState.navigationIconContentDescription
                        )
                    }
                }
            },
            title = {
                val title = appBarState.title
                if (title.isNotEmpty()) {
                    Text(text = title)
                }
            },
            actions = {
                val items = appBarState.actions
                if (items.isNotEmpty()) {
                    ActionsMenu(
                        items = items,
                        isOpen = menuExpanded,
                        onToggleOverflow = { menuExpanded = !menuExpanded },
                        maxVisibleItems = 3,
                        notificationList.value.size
                    )
                }
            },
        )
    } else {
        TopAppBar(
            modifier = modifier
                .background(color = White)
                .graphicsLayer {
                    shadowElevation = 5f
                },
            navigationIcon = {
                val icon = appBarState.navigationIcon
                val callback = appBarState.onNavigationIconClick
                if (icon != null) {
                    IconButton(onClick = { callback?.invoke() }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = appBarState.navigationIconContentDescription
                        )
                    }
                }
            },
            title = {
                val title = appBarState.title
                if (title.isNotEmpty()) {
                    Text(text = title)
                }
            },
            actions = {
                val items = appBarState.actions
                if (items.isNotEmpty()) {
                    ActionsMenu(
                        items = items,
                        isOpen = menuExpanded,
                        onToggleOverflow = { menuExpanded = !menuExpanded },
                        maxVisibleItems = 3,
                        notificationList.value.size
                    )
                }
            },
        )
    }
}