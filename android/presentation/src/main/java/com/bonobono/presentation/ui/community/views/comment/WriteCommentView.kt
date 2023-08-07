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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Comment
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CommonTextField
import com.bonobono.presentation.ui.theme.BackgroundLightGray
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.viewmodel.CommentViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WriteCommentView(
    modifier: Modifier = Modifier,
    type: String,
    articleId: Int,
    onWriteCommentClicked: (Comment) -> Unit,
    commentViewModel: CommentViewModel = hiltViewModel()
) {
    var postContentState by rememberSaveable { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val commentState by commentViewModel.commentState.collectAsStateWithLifecycle()

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
                    onValueChange = { postContentState = it },
                )
            }
            IconButton(
                modifier = modifier.size(44.dp),
                onClick = {
                    if (postContentState.isNotBlank()) {
                        /* TODO("댓글 달기") */
//                        commentViewModel.writeComment(type, articleId, comment = comment)
//                        if (commentState is NetworkResult.Success<Comment>) {
//                            // TODO("댓글 작성 완료 -> 리스트에 넣어주기")
//                            val comment = (commentState as NetworkResult.Success<Comment>).data
//                            commentList.add(comment)
//                        }
                        // TODO("댓글 작성 완료 -> 리스트에 넣어주기")
                        val comment = Comment(content = postContentState)
                        onWriteCommentClicked(comment)
                    }
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

@Preview
@Composable
fun PreviewWriteCommentView() {
    WriteCommentView(type = "free", articleId = 1, onWriteCommentClicked = {})
}