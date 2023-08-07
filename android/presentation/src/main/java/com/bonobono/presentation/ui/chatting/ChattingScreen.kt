package com.bonobono.presentation.ui.chatting

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.bonobono.domain.model.chatting.Chat
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.chatting.view.ChattingCard
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Composable
fun MainChattingScreen(navController: NavController) {
    val chatting = remember { DataProvider.chatList }
    LazyColumn() {

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
            cardType = "edit"
        )
    }
}