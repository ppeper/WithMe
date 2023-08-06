package com.bonobono.presentation.ui.community.views.comment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.domain.model.community.Comment
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.community.util.DummyData.commentList
import com.bonobono.presentation.ui.community.util.DummyData.commentUser
import com.bonobono.presentation.ui.community.util.DummyData.commentUserNotMe
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray


@Composable
fun CommentListView(
    modifier: Modifier = Modifier,
    commentList: List<Comment>
) {
    Column {
        commentList.forEach {
            CommentView(comments = it)
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CommentView(
    modifier: Modifier = Modifier,
    comments: Comment
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(comments.profileUrl)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.size(12.dp))
        Column {
            Text(
                text = comments.nickname,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Black_100,
                )
            )
            Text(
                text = "하루 전",
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
            CommentRow(comments = comments)
            // 대댓글 리스트 TODO
        }
    }
}

@Composable
fun CommentRow(
    modifier: Modifier = Modifier,
    comments: Comment
) {
    var likeState by rememberSaveable { mutableStateOf(comments.liked) }
    var likeCntState by rememberSaveable { mutableStateOf(comments.likes) }
    var commentCntState by rememberSaveable { mutableStateOf(comments.childComments?.size ?: 0) }

    Row(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    /* TODO("서버에 좋아요 클릭 추가") */
                    if (likeState) { likeCntState += 1 } else likeCntState -= 1
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
                    onCheckedChange = { likeState = !likeState },
                    interactionSource = MutableInteractionSource()
                ) {
                    Icon(
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
        // 일반 댓글 -> 대댓글을 달 수 있다.
        if (comments.parentCommentId == null) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_chat),
                    contentDescription = "댓글 아이콘",
                    tint = TextGray
                )
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = "댓글 쓰기",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = modifier.size(4.dp))
                if (0 < commentCntState) {
                    Text(
                        text = commentCntState.toString(),
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = TextGray,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }

}
@Preview
@Composable
fun PreviewCommentRow() {
    CommentRow(comments = commentUser)
}

@Preview
@Composable
fun PreviewCommentList() {
    CommentListView(commentList = commentList)
}

@Preview
@Composable
fun PreviewCommentView() {
    Column {
        CommentView(comments = commentUser)
        CommentView(comments = commentUserNotMe)
    }
}