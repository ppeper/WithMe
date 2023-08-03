package com.bonobono.presentation.ui.community.views.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.community.util.DummyData.commentUser
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray

// TODO("댓글 단 사람들 리스트")
data class TestUser(
    val type: Int? = null,
    val profile: String,
    val name: String,
    val comment: String,
    val userClickLike: Boolean = true,
    val commentList: List<TestUser> = emptyList()
)

@Composable
fun CommentListView(
    modifier: Modifier = Modifier,
    comments: TestUser
) {
    LazyColumn {
        items(comments.commentList) {
            CommentView(comments = comments)
        }
    }
}
@Composable
fun CommentView(
    modifier: Modifier = Modifier,
    comments: TestUser
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(comments.profile)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.size(12.dp))
        Column {
            Text(
                text = comments.name,
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
                text = comments.comment,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Black_100,
                )
            )
            CommentRow(comments = comments)
        }
    }
}

@Composable
fun CommentRow(
    modifier: Modifier = Modifier,
    comments: TestUser
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconToggleButton(
                modifier = modifier.size(20.dp),
                checked = comments.userClickLike,
                colors = IconButtonDefaults.iconToggleButtonColors(
                    checkedContentColor = Red,
                    contentColor = TextGray
                ),
                onCheckedChange = {/* TODO("서버에 좋아요 클릭 추가") */},
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_like),
                    contentDescription = "좋아요 아이콘",
                )
            }
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = "좋아요",
                style = TextStyle(
                    fontSize = 10.sp,
                    color = if (comments.userClickLike) Red else TextGray,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = "4",
                style = TextStyle(
                    fontSize = 10.sp,
                    color = if (comments.userClickLike) Red else TextGray,
                    textAlign = TextAlign.Center,
                )
            )
        }
        if (comments.type != null) {
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
                    text = if (comments.commentList.isEmpty()) "댓글 쓰기" else "댓글",
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
@Preview
@Composable
fun PreviewCommentList() {
    CommentListView(comments = commentUser)
}
@Preview
@Composable
fun PreviewCommentRow() {
    CommentRow(comments = commentUser)
}

@Preview
@Composable
fun PreviewCommentView() {
    CommentView(comments = commentUser)
}