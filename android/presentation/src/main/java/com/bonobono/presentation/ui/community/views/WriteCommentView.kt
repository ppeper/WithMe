package com.bonobono.presentation.ui.community.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.BackgroundLightGray
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun WriteCommentView(
    modifier: Modifier = Modifier,
) {
    val postContentState = remember { mutableStateOf("") }

    Column {
        Divider(color = DividerGray)
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_photo),
                contentDescription = "사진"
            )
            Row(
                modifier = modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(size = 12.dp))
                    .background(color = BackgroundLightGray)
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SelectionContainer(
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),

                    ) {
                    BasicTextField(
                        value = postContentState.value,
                        onValueChange = { postContentState.value = it },
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Black_100,
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        decorationBox = { innerTextField ->
                            if (postContentState.value.isEmpty()) {
                                Text(
                                    text = "댓글을 입력해주세요",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(400),
                                        color = TextGray,
                                    )
                                )
                            }
                            innerTextField()
                        }
                    )
                }
                IconButton(
                    modifier = modifier.size(32.dp)
                        ,
                    onClick = {
                        if (postContentState.value.isNotBlank()) {
                            /* TODO("댓글 달기") */
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color(0xFF005CB9))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_send),
                        contentDescription = "보내기 아이콘"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWriteCommentView() {
    WriteCommentView()
}