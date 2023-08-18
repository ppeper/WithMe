package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.ChatLightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.ChattingViewModel

@Composable
fun FriendChattingView(
    chattingViewModel: ChattingViewModel
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data("")
                .error(R.drawable.default_profile)
                .build(),
            contentDescription = "프로필",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 8.dp))
            .background(color = ChatLightGray)
        ) {
            Text(text = "이건 상대방 테스트입니다",
                style = TextStyle(
                    color = TextGray
                )
            )
        }
        Text(text = "time")
    }
}

@Preview(showBackground = true)
@Composable
fun FriendChattingViewPreview() {
    FriendChattingView(hiltViewModel())
}