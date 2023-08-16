package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.ChatLightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.viewmodel.ChattingViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val TAG = "ChattingCard"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChattingCard(
    chattingList: ChattingList,
    chattingViewModel: ChattingViewModel,
    moveAction: () -> Unit,
    exitAction: () -> Unit
) {
    val currentDate = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val latestTime = chattingList.messageCreatedDate?.let {
        if(it == "00:00:00") LocalDateTime.now()
        else LocalDateTime.parse(it, formatter)
    } ?: LocalDateTime.now() // 만약 null이면 현재 시간으로 초기화

    val formattedDate = when {
        LocalDate.from(latestTime) == LocalDate.from(currentDate) -> latestTime.format(
            DateTimeFormatter.ofPattern("a  hh : mm")
        )

        latestTime == currentDate.minusDays(1) -> "어제"
        (latestTime.isBefore(currentDate.minusDays(1)) && latestTime.year == currentDate.year) -> latestTime.format(
            DateTimeFormatter.ofPattern("MM월 dd일")
        )

        latestTime.year != currentDate.year -> latestTime.format(
            DateTimeFormatter.ofPattern(
                "yyyy.MM.dd"
            )
        )
        else -> latestTime.format((DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")))
    }
    var isExitDialogVisible by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = ChatLightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .combinedClickable(onClick = moveAction,
                onLongClick = { isExitDialogVisible = true }) ,

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
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    ChatProfileImg(
                        profileImg = chattingList.profileImgUrl,
                        profileImgDesc = chattingList.profileImgName)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = chattingList.nickname,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    chattingList.msg?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(2f),
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
                Icon(painter = painterResource(id = R.drawable.ic_bell),
                    modifier = Modifier.size(20.dp),
                    tint = PrimaryBlue,
                    contentDescription = "채팅 알림")

                if (isExitDialogVisible) {
                    ExitDialog(onDismiss = { isExitDialogVisible = false },
                        chattingViewModel = chattingViewModel,
                        roomNumber = chattingList.roomNumber,
                        exitAction = exitAction)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingCardPreview() {
//    ChattingCard( ChattingList(
//        nickname = "황신운",
//        roomNumber = 1,
//        profileImgUrl = "",
//        profileImgName = "프로필 이미지",
//        latestMsg = "이번에 언제 만나나요?",
//        latestTime = ""
//    ), {}
//    )
}