package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.domain.model.chatting.Chat
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.ChatLightGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.White
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val TAG = "ChattingCard"
@Composable
fun ChattingCard(chat: Chat) {
    val currentDate = LocalDateTime.now()
    var radioBtnCheck by remember { mutableStateOf(false) }
    val formattedDate = when {
        LocalDate.from(chat.latestTime) == LocalDate.from(currentDate) -> chat.latestTime.format(DateTimeFormatter.ofPattern("hh : mm a"))
        chat.latestTime == currentDate.minusDays(1) -> "어제"
        (chat.latestTime.isBefore(currentDate.minusDays(1)) && chat.latestTime.year == currentDate.year) -> chat.latestTime.format(DateTimeFormatter.ofPattern("MM월 dd일"))
        chat.latestTime.year != currentDate.year -> chat.latestTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        else -> chat.latestTime.format((DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")))
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = ChatLightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if(chat.cardType == "edit") {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(color = Color.Transparent)
                            .clickable {
                                radioBtnCheck = !radioBtnCheck
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        if (radioBtnCheck) {
                            Icon(painter = painterResource(id = R.drawable.round_check_circle),
                                tint = PrimaryBlue,
                                modifier = Modifier.size(32.dp),
                                contentDescription = "채팅방 선택")
                        } else {
                             Icon(painter = painterResource(id = R.drawable.radio_button_unchecked),
                                 tint = LightGray,
                                 modifier = Modifier.size(32.dp),
                                 contentDescription = "채팅방 미선택")
                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                ChatProfileImg(profileImg = chat.profileImg)
                Spacer(modifier = Modifier.width(16.dp))
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(
                        text = chat.name,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = chat.latestChatStr,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formattedDate,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(color = Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = chat.latestCnt.toString(),
                        style = TextStyle(
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingCardPreview() {
    ChattingCard( Chat(
        name = "황신운",
        profileImg = R.drawable.profile_test,
        latestChatStr = "이번에 언제 만나나요?",
        latestTime = LocalDateTime.now(),
        latestCnt = 3,
        cardType = "normal")
    )
}