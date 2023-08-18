package com.bonobono.presentation.ui.chatting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.presentation.ui.chatting.view.ChattingCard
import com.bonobono.presentation.ui.common.LoadingView
import com.bonobono.presentation.ui.common.topbar.screen.ChattingScreen
import com.bonobono.presentation.viewmodel.ChattingRoomViewModel
import com.bonobono.presentation.viewmodel.ChattingViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MainChattingScreen(
    navController: NavController,
    chattingViewModel: ChattingViewModel = hiltViewModel(),
    chattingRoomViewModel: ChattingRoomViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        ChattingScreen.buttons
            .onEach { button ->
                when (button) {
                    ChattingScreen.AppBarIcons.Search -> { /* TODO("서버에서 게시글 검색")*/
                    }
                }
            }.launchIn(this)
        chattingViewModel.getChattingList()
    }
    val chattingState by chattingViewModel.chattingList.collectAsStateWithLifecycle()
    when (chattingState) {
        is NetworkResult.Loading -> {
            LoadingView()
        }

        is NetworkResult.Success -> {
            val chattingRoomList = (chattingState as NetworkResult.Success<List<ChattingList>>).data
            ChatRoomList(
                chatList = chattingRoomList,
                navController = navController,
                chattingViewModel = chattingViewModel,
                chattingRoomViewModel = chattingRoomViewModel
            )
        }

        else -> {}
    }
}

@Composable
fun ChatRoomList(
    chatList: List<ChattingList>,
    navController: NavController,
    chattingViewModel: ChattingViewModel,
    chattingRoomViewModel: ChattingRoomViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(chatList) { item ->
            ChattingCard(
                chattingList = item,
                moveAction = {
                    chattingRoomViewModel.enterChattingRoom(item.nickname)
//                    navController.navigate("${ChattingEditNav.route}/${item.nickname}")
                },
                exitAction = { chattingViewModel.deleteChattingItem(item) },
                chattingViewModel = chattingViewModel
            )
        }
    }
}

object DataProvider {
    val localDateTime = LocalDateTime.now() // 예시 LocalDateTime 객체

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formattedString = localDateTime.format(formatter)
    val chatList = (1..10).map {
        ChattingList(
            nickname = "신운",
            roomNumber = 1,
            profileImgName = "",
            profileImgUrl = "",
            msg = "test",
            messageCreatedDate = formattedString,
        )
    }
}