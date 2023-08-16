package com.bonobono.presentation.ui.community.views.comment

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.request.ImageRequest
import com.bonobono.domain.model.community.Comment
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.viewmodel.CommentViewModel

@Composable
fun CommentView(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    type: String,
    articleId: Long,
    comments: Comment
) {
    val createDateState by rememberSaveable { mutableStateOf(DateUtils.dateToString(comments.createdDate)) }
    val reComment by remember { mutableStateOf(comments.childComments) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 16.dp,
                end = 16.dp,
                start = comments.parentCommentId?.run { 0.dp } ?: 16.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(comments.profileImg)
                .error(R.drawable.default_profile)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.size(12.dp))
        Column {
            Text(
                text = comments.nickname ?: "홍길동",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Black_100,
                )
            )
            Text(
                text = createDateState,
                style = TextStyle(
                    fontSize = 10.sp,
                    color = TextGray,
                )
            )
            Text(
                text = comments.content,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Black_100,
                )
            )
            CommentRow(type = type, articleId = articleId, comments = comments, focusRequester = focusRequester)
            // 대댓글 리스트
            if (reComment.isNotEmpty()) {
                Log.d("TEST", "CommentView: 대댓글 다시 부르기")
                reComment.forEach { reComment ->
                    CommentView(type = type, articleId = articleId, comments = reComment, focusRequester = focusRequester)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentRow(
    modifier: Modifier = Modifier,
    type: String,
    articleId: Long,
    comments: Comment,
    commentViewModel: CommentViewModel = hiltViewModel(),
    focusRequester: FocusRequester
) {
    var likeState by rememberSaveable { mutableStateOf(comments.liked) }
    var likeCntState by rememberSaveable { mutableStateOf(comments.likes) }
    val commentCntState by rememberSaveable { mutableStateOf(comments.childComments.size) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    // 댓글 좋아요 클릭
                    commentViewModel.updateCommentLike(type, articleId, comments.id)
                    Log.d("TEST", "댓글 좋아요(${comments.id})")
                    if (likeState) {
                        likeCntState -= 1
                    } else likeCntState += 1
                    likeState = !likeState
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconToggleButton(
                    modifier = modifier.size(20.dp),
                    checked = likeState,
                    colors = IconButtonDefaults.iconToggleButtonColors(
                        checkedContentColor = Red,
                        contentColor = TextGray
                    ),
                    onCheckedChange = {
                        // 댓글 좋아요 클릭
                        commentViewModel.updateCommentLike(type, articleId, comments.id)
                        if (likeState) {
                            likeCntState -= 1
                        } else likeCntState += 1
                        likeState = !likeState
                    },
                    interactionSource = MutableInteractionSource()
                ) {
                    Icon(
                        modifier = modifier.size(20.dp),
                        painter = painterResource(if (likeState) R.drawable.ic_like_filled else R.drawable.ic_like),
                        contentDescription = "좋아요 아이콘",
                    )
                }
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = "좋아요",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = if (likeState) Red else TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Spacer(modifier = modifier.size(4.dp))
            if (0 < likeCntState) {
                Text(
                    text = likeCntState.toString(),
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = if (likeState) Red else TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}