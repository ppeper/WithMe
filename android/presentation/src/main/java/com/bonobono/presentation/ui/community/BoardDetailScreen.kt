package com.bonobono.presentation.ui.community

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.community.util.boardDetailLaunchEffect
import com.bonobono.presentation.ui.community.views.DropDownMenuView
import com.bonobono.presentation.ui.community.views.NoCommentView
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.rememberImeState

data class PostDetail(
    val title: String,
    val content: String,
    val images: List<String>? = null,
    val name: String,
    val profile: String,
    val time: Long,
    val viewCount: Int = 0,
)

@Composable
fun BoardDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    postDetail: PostDetail
) {
    boardDetailLaunchEffect(navController = navController)
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(200))
        }
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .then(modifier.padding(16.dp)),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                WriterView(postDetail = postDetail)

                Text(
                    text = postDetail.title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Black_100,
                    )
                )
                Text(
                    text = postDetail.content,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Black_100,
                    )
                )
                postDetail.images?.let {
                    MultipleImageView(images = it)
                }
                Text(
                    text = "조회수 ${postDetail.viewCount}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
            }
            LikeAndCommentView(postDetail = postDetail)
            NoCommentView()
        }
    }
}

@Composable
fun WriterView(
    modifier: Modifier = Modifier,
    postDetail: PostDetail,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileView(postDetail = postDetail)
        // TODO("내가 쓴 글만 DropDown 보이기 -> 로그인 완성되면 Token으로 확인)
        DropDownMenuView(
            onUpdateClick = {},
            onDeleteClick = {},
        )
    }
}

@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    postDetail: PostDetail
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(postDetail.profile)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.size(12.dp))
        Column {
            Text(
                text = postDetail.name,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Black_100,
                )
            )
            Text(
                text = "하루 전",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = TextGray,
                )
            )
        }
    }
}

@Composable
fun LikeAndCommentView(
    modifier: Modifier = Modifier,
    postDetail: PostDetail,
) {
    // TODO("유저가 게시글 좋아요 눌렀는지 기본 세팅값 필요")
    var likeState by rememberSaveable { mutableStateOf(true) }

    Column {
        Divider(color = DividerGray)
        Row(
            modifier = modifier
                .wrapContentHeight()
                .padding(vertical = 16.dp)
        ) {
            Row(
                modifier = modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconToggleButton(
                    modifier = modifier.size(24.dp),
                    checked = likeState,
                    colors = IconButtonDefaults.iconToggleButtonColors(
                        checkedContentColor = Red,
                        contentColor = TextGray
                    ),
                    onCheckedChange = { likeState = !likeState/* TODO("서버에 좋아요 클릭 추가") */ },
                    interactionSource = MutableInteractionSource()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = "좋아요 아이콘"
                    )
                }
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = "좋아요",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = if (likeState) Red else TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = "4",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = if (likeState) Red else TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Row(
                modifier = modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_chat),
                    contentDescription = "댓글 아이콘",
                    tint = TextGray
                )
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = "댓글",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = TextGray,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
        Divider(color = DividerGray)
    }
}

@Preview
@Composable
fun PreviewLikeAndCommentView() {
    LikeAndCommentView(
        postDetail = PostDetail(
            title = "쓰레기들 위치 찍습니다",
            content = "발견된 해수욕장 쓰레기 무단 투기",
            name = "홍길동",
            images = listOf(
                "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
                "https://images.unsplash.com/photo-1682685796063-d2604827f7b3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
            ),
            profile = "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
            time = System.currentTimeMillis()
        )
    )
}

@Preview
@Composable
fun PreviewBoardDetail() {
    BoardDetailScreen(
        postDetail = PostDetail(
            title = "쓰레기들 위치 찍습니다",
            content = "발견된 해수욕장 쓰레기 무단 투기",
            name = "홍길동",
            images = listOf(
                "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
                "https://images.unsplash.com/photo-1682685796063-d2604827f7b3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
            ),
            profile = "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
            time = System.currentTimeMillis()
        ),
        navController = rememberNavController()
    )
}

@Preview
@Composable
fun PreviewWriterView() {
    WriterView(
        postDetail = PostDetail(
            title = "쓰레기들 위치 찍습니다",
            content = "발견된 해수욕장 쓰레기 무단 투기",
            name = "홍길동",
            profile = "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
            time = System.currentTimeMillis()
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultipleImageView(
    modifier: Modifier = Modifier,
    images: List<String>
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        val pageCount = images.size
        val pageState = rememberPagerState()
        HorizontalPager(
            pageCount = pageCount,
            state = pageState
        ) { page ->
            ImageViewWithNumber(number = page, images = images)
        }
        // 업로드 된 이미지 번호
        if (1 < images.size) {
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Box(
                    modifier = modifier
                        .size(80.dp, 36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Black_70)
                        .align(Alignment.Center)
                ) {
                    Text(
                        modifier = modifier.align(Alignment.Center),
                        text = "${pageState.currentPage + 1} / ${images.size}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ImageViewWithNumber(
    modifier: Modifier = Modifier,
    number: Int,
    images: List<String>
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(images[number])
            .build(),
        contentDescription = "업로드 사진",
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun PreviewMultipleImageView() {
    MultipleImageView(
        images = listOf(
            "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
        )
    )
}