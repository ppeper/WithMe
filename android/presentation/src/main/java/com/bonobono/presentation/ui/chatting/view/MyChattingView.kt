package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.ChattingViewModel

@Composable
fun MyChattingView(
    chattingViewModel: ChattingViewModel
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        Text(text = "time")
        Box(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 8.dp))
            .background(color = PrimaryBlue)
            ) {
            Text(text = "이건 테스트입니다",
                style = TextStyle(
                    color = White
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyChattingViewPreview() {
    MyChattingView(hiltViewModel())
}