package com.bonobono.presentation.ui.chatting

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bonobono.presentation.ui.chatting.view.TopChattingRoom
import com.bonobono.presentation.ui.chatting.view.WriteChatView
import com.bonobono.presentation.viewmodel.ChattingViewModel

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChattingRoomScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    roomTitle : String,
    chattingViewModel: ChattingViewModel = hiltViewModel()
) {

    var isTextFieldFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    Scaffold (
        topBar = {
            TopChattingRoom(
                title = roomTitle,
                navController = navController,
                onExitClick = {},
                onReportClick = {}
            )
        },
        bottomBar = {
            WriteChatView(
                modifier = modifier,
                onPicClicked = {},
                onWriteChatClicked = {
                },
                onFocusChanged = {
                    keyboardController?.show()
                    isTextFieldFocused = !isTextFieldFocused
                },
                chattingViewModel = chattingViewModel,
                focusRequester = focusRequester
            )
        }
    ){

    }
}