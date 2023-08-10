package com.bonobono.presentation.ui.community

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.bonobono.domain.model.community.Link
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.community.util.DummyData.dummyArticle
import com.bonobono.presentation.ui.community.util.boardDetailLaunchEffect
import com.bonobono.presentation.ui.community.views.board.DropDownMenuView
import com.bonobono.presentation.ui.community.views.board.ProceedingView
import com.bonobono.presentation.ui.community.views.comment.CommentView
import com.bonobono.presentation.ui.community.views.comment.NoCommentView
import com.bonobono.presentation.ui.community.views.comment.WriteCommentView
import com.bonobono.presentation.ui.community.views.link.LinkImageTitle
import com.bonobono.presentation.ui.community.views.link.getMetaData
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.utils.rememberImeState
import com.bonobono.presentation.viewmodel.CommentViewModel
import com.bonobono.presentation.viewmodel.CommunityViewModel
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoardDetailScreen(
    modifier: Modifier = Modifier,
    type: String,
    articleId: Long,
    navController: NavController,
    communityViewModel: CommunityViewModel = hiltViewModel()
) {
    boardDetailLaunchEffect(navController = navController)
    val imeState = rememberImeState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()
    val articleState by communityViewModel.articleDetailState.collectAsStateWithLifecycle()

//    LaunchedEffect(key1 = imeState.value) {
//        if (imeState.value) {
//            scrollState.animateScrollToItem(scrollState.layoutInfo.totalItemsCount - 1)
//        }
//    }


    // 게시글 정보 불러오기
    LaunchedEffect(Unit) {
        communityViewModel.getArticleById(type, articleId)
    }
    when (articleState) {
        is NetworkResult.Loading -> {

        }

        is NetworkResult.Success -> {
            val article = (articleState as NetworkResult.Success<Article>).data.copy(articleId = articleId)
            var comments by remember { mutableStateOf(article.comments) }
            var isTextFieldFocused by remember { mutableStateOf(false) }
            var commentCnt by remember { mutableStateOf(article.commentCnt) }
            val commentViewModel: CommentViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            var metaLink by remember { mutableStateOf(Link()) }
            LaunchedEffect(Unit) {
                scope.launch {
                    metaLink = getMetaData(Link(article.url, article.urlTitle))
                }
            }
            // Meta Url 파싱 완료
            if (metaLink.isSuccess) {

                Scaffold(
                    bottomBar = {
                        WriteCommentView(
                            modifier = modifier,
                            type = type,
                            articleId = article.articleId,
                            onWriteCommentClicked = { comment ->
                                // 대 댓글 작성
                                if (comment.parentCommentId != null) {
                                    val index = comments.indexOfFirst { it.id == comment.parentCommentId }
                                    if (index != -1) {
                                        comments = comments.apply {
                                            comments[index].childComments = comments[index].childComments.toMutableList().apply { add(comment) }.toList()
                                        }.toList()
                                    }
                                } else {
                                    Log.d("TEST", "BoardDetailScreen: 댓글 추가 $comment")
                                    comments = comments.toMutableList().apply { add(comment) }.toList()
                                }
                                Log.d("TEST", "BoardDetailScreen: 댓글 추가 $comment")
                                // 댓글 수 증가
                                commentCnt++
                            },
                            onFocusChanged = {
                                isTextFieldFocused = !isTextFieldFocused
                            }
                        )
                    }
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(color = White)
                            .padding(it)
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    keyboardController?.hide()
                                    isTextFieldFocused = !isTextFieldFocused
                                    commentViewModel.setCommentId(-1)
                                }
                            }
                    ) {
                        LazyColumn(
                            modifier = modifier.fillMaxSize(),
                            state = scrollState
                        ) {
                            item {
                                Column(
                                    modifier = modifier
                                        .fillMaxSize()
                                        .then(modifier.padding(16.dp)),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {

                                    WriterView(type = type, communityViewModel = communityViewModel, article = article, navController = navController)

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
                                    LinkImageTitle(
                                        link = metaLink,
                                        R.drawable.ic_go
                                    ) {
                                        val encodedUrl = URLEncoder.encode(metaLink.url, StandardCharsets.UTF_8.toString())
                                        navController.navigate("${NavigationRouteName.LINK_WEB_VIEW}/$encodedUrl")
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
                                // 게시글 좋아요 추가
                                LikeAndCommentView(article = article, commentCnt = commentCnt) {
                                    communityViewModel.updateArticleLike(type, articleId)
                                }
                            }

                            if (article.comments.isEmpty()) {
                                item { NoCommentView() }
                            } else {
                                items(comments) { item ->
                                    CommentView(type = type, articleId = articleId, comments = item)
                                }
                            }
                        }
                    }
                }
            }
        }

        is NetworkResult.Error -> {
        }
    }
}

@Composable
fun WriterView(
    modifier: Modifier = Modifier,
    type: String,
    communityViewModel: CommunityViewModel,
    article: Article,
    navController: NavController
) {

    val deleteState by communityViewModel.deleteArticleState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (deleteState is NetworkResult.Success<Unit>) {
            navController.popBackStack()
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileView(article = article)
        Spacer(modifier = modifier.weight(1f))
        if (article.type != Constants.FREE) {
            ProceedingView(type = article.type, isProceeding = article.recruitStatus)
        }
        // TODO("내가 쓴 글만 DropDown 보이기 -> 로그인 완성되면 Token으로 확인)
        DropDownMenuView(
            onUpdateClick = {},
            onDeleteClick = {
                communityViewModel.deleteArticle(type, article.articleId)
            },
            onFinishClick = {},
            article = article
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
                .error(R.drawable.default_profile)
                .build(),
            contentDescription = "프로필",
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
    commentCnt: Int,
    onLikeClick: () -> Unit,
) {
    var likeState by rememberSaveable { mutableStateOf(article.liked) }
    var likeCntState by rememberSaveable { mutableStateOf(article.likes) }

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
                        onLikeClick()
                        if (likeState) {
                            likeCntState -= 1
                        } else {
                            likeCntState += 1
                        }
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
                        onCheckedChange = {
                            onLikeClick()
                            if (likeState) {
                                likeCntState -= 1
                            } else {
                                likeCntState += 1
                            }
                            likeState = !likeState
                        },
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
                    text = commentCnt.toString(),
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
        article = dummyArticle,
        commentCnt = 5,
        onLikeClick = {}
    )
}

@Preview
@Composable
fun PreviewWriterView() {
    WriterView(
        type = "free",
        communityViewModel = hiltViewModel(),
        article = dummyArticle,
        navController = rememberNavController()
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