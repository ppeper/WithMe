package com.bonobono.presentation.ui.community

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.bonobono.presentation.ui.common.topbar.screen.CommunityListScreen
import com.bonobono.presentation.ui.community.views.board.BoardListView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun CommunityScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        CommunityListScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityListScreen.AppBarIcons.Search -> { /* TODO("서버에서 게시글 검색")*/ }
                }
            }.launchIn(this)
    }
    BoardListView(navController = navController)
}