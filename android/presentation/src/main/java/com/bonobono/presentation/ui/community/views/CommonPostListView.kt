package com.bonobono.presentation.ui.community.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.Green
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White

// TODO("서버와 데이터 맞추기")
data class BoardItem(
    // 커뮤니티 구분 값
    val communityId: Int = 0,
    val title: String,
    val content: String,
    val images: List<String> = emptyList(),
    val like: Int = 0,
    val comment: Int = 0,
    val isProceeding: Boolean = false,
    // 글쓴 유저
    val userName: String = "비공개",
    val userProfile: String = "",
    val time: Long = System.currentTimeMillis()
)

@Composable
fun CommonPostListView(
    boardList: List<BoardItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(boardList) { item ->
            BoardItemView(item = item)
        }
    }
}

@Composable
fun BoardItemView(
    modifier: Modifier = Modifier,
    item: BoardItem
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(136.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier
                    .align(Alignment.Top)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // 자유게시판이 아니면 진행사항 view 보여주기
                if (item.communityId == 1) {
                    ProceedingView(isProceeding = item.isProceeding)
                } else {
                    Spacer(modifier = modifier.size(8.dp))
                }
                Text(
                    text = "쓰레기 모은 곳 제보합니다",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF191919),
                    )
                )
                Text(
                    text = "여기 OO해수욕장 앞에 쓰레기 많아요",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = TextGray
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = modifier
                            .size(24.dp),
                        border = BorderStroke(1.dp, color = Color(0xFFEBEBEB)),
                        shape = CircleShape
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_board_question),
                            contentDescription = "프로필 이미지",
                            contentScale = ContentScale.Crop
                        )
                    }
                    // 이름
                    Text(
                        text = "허태식",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF191919),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(2.dp)
                            .height(2.dp)
                            .clip(CircleShape)
                            .background(color = TextGray)
                    )
                    // 업로드 시간
                    Text(
                        text = "3시간 전",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(400),
                            color = TextGray,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                // 이미지가 게시글에 등록되어 있을때
                if (item.images.isNotEmpty()) {
                    BoardPhotoView(images = item.images)
                } else {
                    Spacer(modifier = modifier.size(64.dp))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoardCountView(item)
                }
            }
        }
    }
}

@Composable
fun BoardCountView(item: BoardItem) {
    if (item.like > 0) {
        Image(
            painter = painterResource(id = R.drawable.ic_like),
            contentDescription = "좋아요 개수",
        )
        Text(
            text = item.like.toString(),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = TextGray,
                textAlign = TextAlign.Center,
            )
        )
    }
    if (item.comment > 0) {
        Image(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = "댓글 개수",
        )
        Text(
            text = item.like.toString(),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = TextGray,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun ProceedingView(
    modifier: Modifier = Modifier,
    isProceeding: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(modifier = modifier
            .padding(1.dp)
            .width(8.dp)
            .height(8.dp)
            .clip(CircleShape)
            .background(
                color = if (isProceeding) { Green } else { DarkGray }
            )
        )
        Text(
            text = if (isProceeding) "모집중" else "모집 마감",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = if (isProceeding) { Green } else { DarkGray },
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun BoardPhotoView(
    modifier: Modifier = Modifier,
    images: List<String>
) {
    Box(
        modifier = modifier.size(64.dp)
    ) {
        Image(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = R.drawable.ic_board_free),
            contentDescription = "게시글 이미지",
            contentScale = ContentScale.Crop
        )
        // 이미지 개수에 대한 Box layout
        if (1 < images.size) {
            Box(
                modifier = modifier
                    .size(24.dp)
                    .background(
                        color = Black_70,
                        shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .align(Alignment.BottomEnd),
            ) {
                Text(
                    modifier = modifier.align(Alignment.Center),
                    text = images.size.toString(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = White,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBoardList() {
    val list = listOf(
        BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            comment = 3,
            like = 3,
        ),
        BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf("test", "test", "test"),
            like = 3,
        ),
        BoardItem(
            communityId = 1,
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            images = listOf("test"),
            comment = 3,
            isProceeding = true
        )
    )
    CommonPostListView(boardList = list)
}

@Preview
@Composable
fun PreviewBoardItem() {
    BoardItemView(
        item = BoardItem(
            title = "테스트 타이틀",
            content = "내용은 다음과 같습니다.",
            comment = 3,
            like = 2,
        )
    )
}