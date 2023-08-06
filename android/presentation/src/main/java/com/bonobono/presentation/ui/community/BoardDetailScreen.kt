package com.bonobono.presentation.ui.community

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Image
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.community.util.DummyData.dummyArticle
import com.bonobono.presentation.ui.community.util.boardDetailLaunchEffect
import com.bonobono.presentation.ui.community.views.board.DropDownMenuView
import com.bonobono.presentation.ui.community.views.comment.CommentListView
import com.bonobono.presentation.ui.community.views.comment.NoCommentView
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.utils.rememberImeState
import com.bonobono.presentation.viewmodel.CommunityViewModel

@Composable
fun BoardDetailScreen(
    modifier: Modifier = Modifier,
    type: String,
    articleId: Int,
    navController: NavController,
    communityViewModel: CommunityViewModel = hiltViewModel()
) {
    boardDetailLaunchEffect(navController = navController)
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(200))
        }
    }

    val state by communityViewModel.articleDetailState.collectAsStateWithLifecycle()

    // 게시글 정보 불러오기
    LaunchedEffect(Unit) {
        communityViewModel.getArticleById(type, articleId)
    }
    when (state) {
        is NetworkResult.Loading -> {

        }
        is NetworkResult.Success -> {
            val article = (state as NetworkResult.Success<Article>).data
            Box(
                modifier = modifier.fillMaxSize()
                    .background(color = White)
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

                        WriterView(article = article)

                        Text(
                            text = article.title,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(700),
                                color = Black_100,
                            )
                        )
                        Text(
                            text = article.content,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                color = Black_100,
                            )
                        )
                        if (article.images.isNotEmpty()) {
                            MultipleImageView(images = article.images)
                        }
                        Text(
                            text = "조회수 ${article.views}",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = TextGray,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                    LikeAndCommentView(article = article)
                    Column(
                        modifier = modifier.padding(horizontal = 16.dp)
                    ) {
                        if (article.comments.isEmpty()) {
                            NoCommentView()
                        } else {
                            CommentListView(commentList = article.comments)
                        }
                    }
                }
            }
        }
        is NetworkResult.Error -> {}
    }
}

@Composable
fun WriterView(
    modifier: Modifier = Modifier,
    article: Article,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileView(article = article)
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
    article: Article
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.profileImg)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.size(12.dp))
        Column {
            Text(
                text = article.nickname,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Black_100,
                )
            )
            Text(
                text = DateUtils.dateToString(article.createdDate),
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
    article: Article,
) {
    // TODO("유저가 게시글 좋아요 눌렀는지 기본 세팅값 필요")
    var likeState by rememberSaveable { mutableStateOf(article.liked) }
    var likeCntState by rememberSaveable { mutableStateOf(article.likes) }
    var commentCntState by rememberSaveable { mutableStateOf(article.commentCnt) }

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
            ) {
                Row(
                    modifier = modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        /* TODO("서버에 좋아요 클릭 추가") */
                          likeState = !likeState
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconToggleButton(
                        modifier = modifier.size(24.dp),
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
                        text = likeCntState.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = if (likeState) Red else TextGray,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
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
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = commentCntState.toString(),
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
        article = dummyArticle
    )
}

@Preview
@Composable
fun PreviewWriterView() {
    WriterView(
        article = dummyArticle
    )
}

@Preview
@Composable
fun PreviewBoardDetail() {
    BoardDetailScreen(
        type = "free",
        articleId = 1,
        navController = rememberNavController()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultipleImageView(
    modifier: Modifier = Modifier,
    images: List<Image>
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
    images: List<Image>
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(images[number].imageUrl)
            .build(),
        contentDescription = "업로드 사진",
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}