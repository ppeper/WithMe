package com.bonobono.presentation.ui.community.views.comment

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
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
import com.bonobono.presentation.utils.addFocusCleaner
import com.bonobono.presentation.utils.rememberImeState
import com.bonobono.presentation.viewmodel.CommentViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WriteCommentView(
    modifier: Modifier = Modifier,
    type: String,
    articleId: Long,
    onWriteCommentClicked: (Comment) -> Unit,
    onFocusChanged: () -> Unit,
    commentViewModel: CommentViewModel = hiltViewModel(),
    focusRequester: FocusRequester
) {
    var postContentState by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var commentState by commentViewModel.commentState
    val commentId by commentViewModel.commentId.collectAsStateWithLifecycle()

    if (commentState is NetworkResult.Success) {
        Log.d("TEST", "WriteCommentView: SUCCESS")
        val comment = (commentState as NetworkResult.Success<Comment>).data
        onWriteCommentClicked(comment)
        commentState = NetworkResult.Loading
    } else {
        Log.d("TEST", "WriteCommentView: $commentState")
    }

    Column {
        Divider(color = DividerGray)
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    hint = if (commentId == -1L) "댓글을 입력해주세요" else "답글을 입력해주세요",
                    onValueChange = { postContentState = it },
                    onFocusChange = { onFocusChanged() },
                    focusRequester = focusRequester
                )
            }
            IconButton(
                modifier = modifier.size(48.dp),
                onClick = {
                    if (postContentState.isNotBlank()) {
                        // 대댓글
                        val comment = if (commentId != -1L) {
                            Comment(parentCommentId = commentId, content = postContentState)
                        } else {
                            Comment(content = postContentState)
                        }
                        commentViewModel.writeComment(type, articleId, comment = comment)
                    }
                    keyboardController?.hide()
                    commentViewModel.setCommentId(-1)
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
    WriteCommentView(type = "free", articleId = 1, onWriteCommentClicked = {  }, onFocusChanged = {}, focusRequester = FocusRequester())
}