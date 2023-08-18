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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import com.bonobono.presentation.ui.common.LoadingView
import com.bonobono.presentation.ui.community.util.DummyData.dummyArticle
import com.bonobono.presentation.ui.community.util.boardDetailLaunchEffect
import com.bonobono.presentation.ui.community.views.board.DropDownMenuView
import com.bonobono.presentation.ui.community.views.board.ProceedingView
import com.bonobono.presentation.ui.community.views.comment.CommentView
import com.bonobono.presentation.ui.community.views.comment.NoCommentView
import com.bonobono.presentation.ui.community.views.comment.NoValidUserView
import com.bonobono.presentation.ui.community.views.comment.WriteCommentView
import com.bonobono.presentation.ui.community.views.link.LinkImageTitle
import com.bonobono.presentation.ui.community.views.link.getMetaData
import com.bonobono.presentation.ui.community.views.map.ReportMapAndLocation
import com.bonobono.presentation.ui.community.views.profile.ProfileSheetContent
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.viewmodel.ChattingRoomViewModel
import com.bonobono.presentation.viewmodel.CommentViewModel
import com.bonobono.presentation.viewmodel.CommunityViewModel
import com.bonobono.presentation.viewmodel.SharedLocalViewModel
import com.naver.maps.geometry.LatLng
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BoardDetailScreen(
    modifier: Modifier = Modifier,
    type: String,
    articleId: Long,
    navController: NavController,
    communityViewModel: CommunityViewModel = hiltViewModel(),
    showSnackBar: () -> Unit
) {
    boardDetailLaunchEffect(navController = navController)

    val keyboardController = LocalSoftwareKeyboardController.current
    val snackBarHostState = remember { SnackbarHostState() }
    val scrollState = rememberLazyListState()
    val articleState by communityViewModel.articleDetailState.collectAsStateWithLifecycle()

    // 게시글 정보 불러오기
    LaunchedEffect(Unit) {
        communityViewModel.getArticleById(type, articleId)
    }

    when (articleState) {
        is NetworkResult.Loading -> {
            LoadingView()
        }

        is NetworkResult.Success -> {
            val localViewModel: SharedLocalViewModel = hiltViewModel()
            val chattingRoomViewModel : ChattingRoomViewModel = hiltViewModel()
            val currentMemberId = localViewModel.getMemberId("member_id").toLong()
            val role = localViewModel.getRole("role")
            var showSheet by remember { mutableStateOf(false) }

            val result =
                (articleState as NetworkResult.Success<Article>).data.copy(articleId = articleId)
            val article by remember { mutableStateOf(result) }
            var recruitState by remember { mutableStateOf(article.recruitStatus) }
            var adminState by remember { mutableStateOf(article.adminConfirmStatus) }
            var comments by remember { mutableStateOf(article.comments) }
            var isTextFieldFocused by remember { mutableStateOf(false) }
            var commentCnt by remember { mutableStateOf(article.commentCnt) }
            val commentViewModel: CommentViewModel = hiltViewModel()
            val scope = rememberCoroutineScope()
            var metaLink by remember { mutableStateOf(Link()) }
            val focusManager = LocalFocusManager.current
            val focusRequester by remember { mutableStateOf(FocusRequester()) }
            // 함께 게시판용 링크
            LaunchedEffect(Unit) {
                scope.launch {
                    metaLink = getMetaData(Link(article.url ?: "https://", article.urlTitle ?: ""))
                }
            }

            // 프로필 바텀 시트 뷰
            if (showSheet && (article.memberId != currentMemberId)) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    containerColor = White,
                    tonalElevation = 0.dp,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ) {
                    ProfileSheetContent(
                        modifier = modifier,
                        article = article,
                    ) {
                        chattingRoomViewModel.enterChattingRoom(nickName = article.nickname)
//                        navController.navigate("${ChattingEditNav.route}/${article.nickname}")
                    }
                }
            }
            // Meta Url 파싱 완료
            if (metaLink.isSuccess) {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarHostState) },
                    bottomBar = {
                        // 신고 게시판 -> 글쓴이, 관리자만 댓글 작성 가능
                        if (type == NavigationRouteName.COMMUNITY_REPORT &&
                            (article.memberId != currentMemberId && role != Constants.ADMIN_ROLE)
                        ) {
                            NoValidUserView()
                        } else {
                            WriteCommentView(
                                modifier = modifier,
                                type = type,
                                articleId = article.articleId!!,
                                onWriteCommentClicked = { comment ->
                                    // 대 댓글 작성
                                    if (comment.parentCommentId != null) {
                                        val index =
                                            comments.indexOfFirst { it.id == comment.parentCommentId }
                                        if (index != -1) {
                                            comments = comments.apply {
                                                comments[index].childComments =
                                                    comments[index].childComments.toMutableList()
                                                        .apply { add(comment) }.toList()
                                            }.toMutableList()
                                        }
                                    } else {
                                        comments = comments.toMutableList().apply { add(comment) }
                                            .toMutableList()
                                    }
                                    // 댓글 수 증가
                                    commentCnt++
                                    scope.launch {
                                        snackBarHostState.showSnackbar("댓글이 작성되었습니다.")
                                    }
                                },
                                onFocusChanged = {
                                    keyboardController?.show()
                                    isTextFieldFocused = !isTextFieldFocused
                                },
                                focusRequester = focusRequester
                            )
                        }
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
                                    focusManager.clearFocus()
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
                                    WriterView(role = role,
                                        memberId = currentMemberId,
                                        type = type,
                                        communityViewModel = communityViewModel,
                                        article = article,
                                        navController = navController,
                                        adminCompleteState = adminState,
                                        recruitCompleteState = recruitState,
                                        onRecruitCompleteClicked = {
                                            article.recruitStatus = true
                                            recruitState = true
                                            scope.launch {
                                                snackBarHostState.showSnackbar("모집완료 되었습니다.")
                                            }
                                        },
                                        onAdminCompleteClicked = {
                                            article.adminConfirmStatus = true
                                            adminState = true
                                            scope.launch {
                                                snackBarHostState.showSnackbar("답변완료 되었습니다.")
                                            }
                                        },
                                        onProfileClicked = { showSheet = true },
                                        showSnackBar = { showSnackBar() }
                                    )

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
                                    // 함게 게시판 링크 뷰
                                    if (article.type == Constants.TOGETHER) {
                                        LinkImageTitle(
                                            link = metaLink,
                                            R.drawable.ic_go
                                        ) {
                                            val encodedUrl = URLEncoder.encode(
                                                metaLink.url,
                                                StandardCharsets.UTF_8.toString()
                                            )
                                            navController.navigate("${NavigationRouteName.LINK_WEB_VIEW}/$encodedUrl")
                                        }
                                    }
                                    // 신고 게시판 map view
                                    with(article) {
                                        if (latitude != null && longitude != null && locationName != null) {
                                            ReportMapAndLocation(
                                                mapState = LatLng(
                                                    latitude!!,
                                                    longitude!!
                                                ), locationName = locationName!!
                                            )
                                        }
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

                            if (comments.isEmpty()) {
                                item { NoCommentView() }
                            } else {
                                Log.d("TEST", "BoardDetailScreen: 댓글 다시 불림")
                                items(comments) { item ->
                                    Log.d("TEST", "BoardDetailScreen: 댓글 다시 리컴포저블")
                                    CommentView(
                                        type = type,
                                        articleId = articleId,
                                        comments = item,
                                        focusRequester = focusRequester
                                    )
                                }
                            }
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
    role: String?,
    memberId: Long,
    type: String,
    communityViewModel: CommunityViewModel,
    article: Article,
    recruitCompleteState: Boolean?,
    adminCompleteState: Boolean?,
    navController: NavController,
    onRecruitCompleteClicked: () -> Unit,
    onAdminCompleteClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    showSnackBar: () -> Unit
) {

    val deleteState by communityViewModel.deleteArticleState.collectAsStateWithLifecycle()
    val recruitState by communityViewModel.recruitCompleteState.collectAsStateWithLifecycle()
    val adminState by communityViewModel.adminCompleteState.collectAsStateWithLifecycle()

    LaunchedEffect(deleteState) {
        if (deleteState is NetworkResult.Success<Unit>) {
            Log.d("TEST", "WriterView: $deleteState")
            navController.popBackStack()
            showSnackBar()
        }
    }

    LaunchedEffect(recruitState) {
        if (recruitState is NetworkResult.Success<Unit>) {
            onRecruitCompleteClicked()
        }
    }

    LaunchedEffect(adminState) {
        if (adminState is NetworkResult.Success<Unit>) {
            onAdminCompleteClicked()
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileView(article = article) { onProfileClicked() }
        Spacer(modifier = modifier.weight(1f))
        // 함께 게시판
        if (article.type == Constants.TOGETHER) {
            article.recruitStatus?.let {
                ProceedingView(
                    type = article.type!!,
                    isProceeding = recruitCompleteState!!
                )
            }
        }
        // 신고 게시판
        Log.d("TEST", "WriterView: $article")
        if (article.type == null) {
            article.adminConfirmStatus?.let {
                ProceedingView(
                    type = Constants.REPORT,
                    isProceeding = adminCompleteState!!
                )
            }
        }
        if (article.memberId == memberId) {
            DropDownMenuView(
                role = role,
                memberId = memberId,
                article = article,
                onDeleteClick = {
                    article.articleId?.let { communityViewModel.deleteArticle(type, it) }
                },
                onFinishClick = {
                    if (article.type == Constants.TOGETHER) {
                        article.articleId?.let { communityViewModel.recruitComplete(type, it) }
                    } else {
                        article.articleId?.let { communityViewModel.adminComplete(it) }
                    }
                }
            )

        }
    }
}

@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    article: Article,
    onProfileClicked: () -> Unit
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = MutableInteractionSource(),
            indication = null
        ) {
            onProfileClicked()
        },
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
        memberId = 1,
        role = "ADMIN",
        communityViewModel = hiltViewModel(),
        article = dummyArticle,
        adminCompleteState = null,
        recruitCompleteState = null,
        navController = rememberNavController(),
        onRecruitCompleteClicked = {},
        onAdminCompleteClicked = {},
        onProfileClicked = {}
    ) {}
}

@Preview
@Composable
fun PreviewBoardDetail() {
    BoardDetailScreen(
        type = "free",
        articleId = 1,
        navController = rememberNavController()
    ) {}
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