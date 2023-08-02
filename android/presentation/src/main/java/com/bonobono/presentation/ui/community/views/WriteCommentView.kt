package com.bonobono.presentation.ui.community.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
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
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray

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
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_photo),
                contentDescription = "사진"
            )
            Row(
                modifier = modifier
                    .weight(1f)
                    .height(36.dp)
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
                        value = postContentState,
                        onValueChange = { postContentState = it },
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Black_100,
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        decorationBox = { innerTextField ->
                            if (postContentState.isEmpty()) {
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
                interactionSource = MutableInteractionSource()
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