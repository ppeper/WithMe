package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bonobono.presentation.ui.common.text.CustomTextStyle.primaryColorBtnText
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.ChattingViewModel

@Composable
fun ExitDialog(onDismiss: (Boolean) -> Unit,
               chattingViewModel: ChattingViewModel,
               roomNumber : Int,
               exitAction : () -> Unit) {
    Dialog(onDismissRequest = { onDismiss(false) }) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = White, shape = RoundedCornerShape(8.dp))) {
            Column(modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 28.dp
            ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "채팅방 나가기",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "나가기를 하면 모든 대화 내용이 삭제됩니다.",
                    style = TextStyle(
                        fontSize = 12.sp,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    Button(
                        onClick = { onDismiss(false) },
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(0.dp, 0.dp, 16.dp, 0.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightGray)
                    ) {
                        Text(text = "취소",
                            style = primaryColorBtnText)
                    }
                    Button(
                        onClick = {
                            // viewmodel 사용해서 채팅방 삭제하기
                            chattingViewModel.deleteChattingRoom(roomNumber)
                            exitAction
                            onDismiss(false) },
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(16.dp, 0.dp, 0.dp, 0.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                    ) {
                        Text(text = "확인",
                            style = primaryColorBtnText)
                    }
                }
            }
        }
    }
}