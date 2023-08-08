package com.bonobono.presentation.ui.chatting

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.bonobono.domain.model.chatting.Chat
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.chatting.view.ChattingCard
import java.time.LocalDateTime

@Composable
fun MainChattingScreen(navController: NavController) {
    val chatting = remember { DataProvider.chatList }
    LazyColumn {
        items(chatting) {chatInfo ->
            ChattingCard(chat = chatInfo)
        }
    }
}


object DataProvider {
    val chatList = (1..10).map {
        Chat(
            name = "신운",
            profileImg = R.drawable.profile_test,
            latestChatStr = "test",
            latestTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0),
            latestCnt = 3,
            cardType = "normal"
        )
    }
}