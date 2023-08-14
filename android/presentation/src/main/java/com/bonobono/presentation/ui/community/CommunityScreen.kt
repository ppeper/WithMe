package com.bonobono.presentation.ui.community

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.bonobono.presentation.ui.community.views.board.BoardListView

@Composable
fun CommunityScreen(
    navController: NavController
) {
    BoardListView(navController = navController)
}