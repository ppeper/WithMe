package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.mypage.view.MyPageProfileImg
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.White
import java.util.Date

@Composable
fun ChattingCard(name:String,
                 latestChatStr : String,
                 latestTime : Date,
                 latestCnt : Int) {
    Card {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {

                MyPageProfileImg()
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = name,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = latestChatStr,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = latestTime.toString(),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .border(
                            width = 1.dp,
                            color = White,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(color = Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = latestCnt.toString(),
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

@Composable
fun ChattingEditCard(name:String,
                 latestChatStr : String,
                 latestTime : Date,
                 latestCnt : Int) {
    Card {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { /*TODO*/ }) {
//                    Icon(painter = , contentDescription = )
                }
                MyPageProfileImg()
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = name,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = latestChatStr,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = latestTime.toString(),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .border(
                            width = 1.dp,
                            color = White,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(color = Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = latestCnt.toString(),
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
    ChattingEditCard(name = "황신운", latestChatStr = "이번에 언제 만나나요?", latestTime = Date(), latestCnt = 3)
}