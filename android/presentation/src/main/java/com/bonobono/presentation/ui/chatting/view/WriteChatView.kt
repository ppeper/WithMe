package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.domain.model.community.Comment
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CommonTextField
import com.bonobono.presentation.ui.theme.BackgroundLightGray
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.viewmodel.ChattingViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WriteChatView(
    modifier: Modifier = Modifier,
    onPicClicked: () -> Unit,
    onWriteChatClicked: (Comment) -> Unit,
    onFocusChanged: () -> Unit,
    chattingViewModel: ChattingViewModel,
    focusRequester: FocusRequester
) {
    var postContentState by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

//    if (commentState is NetworkResult.Success) {
//        Log.d("TEST", "WriteCommentView: SUCCESS")
//        val comment = (commentState as NetworkResult.Success<Comment>).data
//        onWriteCommentClicked(comment)
//        commentState = NetworkResult.Loading
//    } else {
//        Log.d("TEST", "WriteCommentView: $commentState")
//    }

    Column {
        Divider(color = DividerGray)
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PicIcon(
                modifier = modifier,
                R.drawable.ic_photo,
                "사진",
                onPicClicked
            )
            Row(
                modifier = modifier
                    .weight(1f)
                    .height(44.dp)
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
                    hint = "",
                    onValueChange = { postContentState = it },
                    onFocusChange = { onFocusChanged() },
                    focusRequester = focusRequester
                )
            }
            IconButton(
                modifier = modifier.size(48.dp),
                onClick = {
                    postContentState = ""
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


@Composable
fun PicIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    onClickListener: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(0.dp,0.dp,16.dp,0.dp)
            .clip(CircleShape)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onClickListener()
            }
    ) {
        Row(
            modifier = modifier
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = text
            )
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = TextGray
                )
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewWriteCommentView() {
//    WriteChatView(onWriteChatClicked = {  }, onPicClicked = {}, onFocusChanged = {}, focusRequester = FocusRequester())
//}