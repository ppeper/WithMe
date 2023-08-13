package com.bonobono.presentation.ui.chatting

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.bonobono.presentation.ui.chatting.view.TopChattingRoom

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChattingRoomScreen(
    navController: NavController,
    roomTitle : String
) {
    Scaffold (
        topBar = {
            TopChattingRoom(
                title = roomTitle,
                navController = navController,
                onExitClick = {},
                onReportClick = {}
            )
        }
    ){

    }
}