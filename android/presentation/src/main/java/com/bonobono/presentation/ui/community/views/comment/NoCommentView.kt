package com.bonobono.presentation.ui.community.views.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun NoCommentView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_no_comment),
            contentDescription = "댓글없음 이미지"
        )
        Text(
            text = "아직 댓글이 없습니다.",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Black_70,
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "가장 먼저 첫 댓글을 달아주세요!",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = TextGray,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview
@Composable
fun PreviewNoCommentView() {
    NoCommentView()
}