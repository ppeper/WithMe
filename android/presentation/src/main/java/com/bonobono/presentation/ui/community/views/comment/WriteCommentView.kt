package com.bonobono.presentation.ui.community.views.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CommonTextField
import com.bonobono.presentation.ui.theme.BackgroundLightGray
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue

@Composable
fun WriteCommentView(
    modifier: Modifier = Modifier,
) {
    var postContentState by rememberSaveable { mutableStateOf("") }

    Column {
        Divider(color = DividerGray)
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO("갤러리 이동") */ },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_photo),
                    contentDescription = "사진",
                )
            }
            Row(
                modifier = modifier
                    .weight(1f)
                    .height(36.dp)
                    .clip(RoundedCornerShape(size = 12.dp))
                    .background(color = BackgroundLightGray)
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CommonTextField(
                    text = postContentState,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Black_100,
                    ),
                    singleLine = true,
                    hint = "댓글을 입력해주세요",
                    onValueChange = { postContentState = it }
                )
            }
            IconButton(
                modifier = modifier.size(44.dp),
                onClick = {
                    if (postContentState.isNotBlank()) {
                        /* TODO("댓글 달기") */
                        postContentState = ""
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = PrimaryBlue,
                    disabledContentColor = LightGray
                ),
                enabled = postContentState.isNotBlank(),
            ) {
                Icon(
                    modifier = modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = "보내기 아이콘",
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewWriteCommentView() {
    WriteCommentView()
}