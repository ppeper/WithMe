package com.bonobono.presentation.ui.community.views.board

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.BoardDetailNav
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.LoadingView
import com.bonobono.presentation.ui.common.button.CommunityFloatingActionButton
import com.bonobono.presentation.ui.community.util.DummyData.dummyArticle
import com.bonobono.presentation.ui.community.util.freeLaunchEffect
import com.bonobono.presentation.ui.community.util.reportLaunchEffect
import com.bonobono.presentation.ui.community.util.withLaunchEffect
import com.bonobono.presentation.ui.mypage.view.EmptyListAnimation
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.Green
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.viewmodel.CommunityViewModel

@Composable
fun CommonPostListView(
    type: String,
    navController: NavController,
    communityViewModel: CommunityViewModel = hiltViewModel()
) {
    freeLaunchEffect(type = type, navController = navController)
    withLaunchEffect(type = type, navController = navController)
    reportLaunchEffect(type = type, navController = navController)

    // 게시글 가져오기
    LaunchedEffect(Unit) {
        communityViewModel.getArticleList(type)
    }
    val state by communityViewModel.articleState.collectAsStateWithLifecycle()

    when (state) {
        is NetworkResult.Loading -> {
            LoadingView()
        }

        is NetworkResult.Success -> {
            val articleList = (state as NetworkResult.Success<List<Article>>).data
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            Scaffold(
                floatingActionButton = {
                    when (currentRoute) {
                        NavigationRouteName.COMMUNITY_FREE -> {
                            CommunityFloatingActionButton(
                                navController = navController,
                                item = CommunityFab.FREE
                            )
                        }

                        NavigationRouteName.COMMUNITY_WITH -> {
                            CommunityFloatingActionButton(
                                navController = navController,
                                item = CommunityFab.WITH
                            )
                        }

                        NavigationRouteName.COMMUNITY_REPORT -> {
                            CommunityFloatingActionButton(
                                navController = navController,
                                item = CommunityFab.REPORT
                            )
                        }
                    }
                }
            ) {
                if (articleList.isEmpty()) {
                    EmptyListAnimation()
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(it),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        items(articleList) { item ->
                            BoardItemView(
                                type = type,
                                article = item,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }

        is NetworkResult.Error -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardItemView(
    modifier: Modifier = Modifier,
    type: String,
    article: Article,
    navController: NavController
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            if (article.articleId == null) {
                article.reportId?.let { navController.navigate("${BoardDetailNav.route}/$type/$it") }
            } else {
                article.articleId?.let { navController.navigate("${BoardDetailNav.route}/$type/$it") }
            }
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(136.dp)
                .background(White)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier
                    .width(260.dp)
                    .align(Alignment.Top)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (article.type == null) {
                    article.adminConfirmStatus?.let { status ->
                        ProceedingView(type = Constants.REPORT, isProceeding = status)
                    }
                } else if (article.type == Constants.TOGETHER) {
                    article.recruitStatus?.let { status ->
                        article.type?.let {
                            ProceedingView(type = it, isProceeding = status)
                        }
                    }
                } else {
                    Spacer(modifier = modifier.size(8.dp))
                }
                Text(
                    text = article.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Black_100,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = article.content,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = TextGray
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(article.profileImg)
                                .error(R.drawable.default_profile)
                                .build(),
                            contentDescription = "프로필",
                            contentScale = ContentScale.Crop
                        )
                    }
                    // 이름
                    Text(
                        text = article.nickname,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Black_100,
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
                        text = DateUtils.dateToString(article.createdDate),
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
                article.mainImage?.let {
                    BoardPhotoView(photoUrl = it.imageUrl, count = article.imageSize)
                } ?: run {
                    Spacer(modifier = modifier.size(64.dp))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoardCountView(article)
                }
            }
        }
    }
}

@Composable
fun BoardCountView(article: Article) {
    if (article.likes > 0) {
        Image(
            painter = painterResource(id = R.drawable.ic_like),
            contentDescription = "좋아요 개수",
        )
        Text(
            text = article.likes.toString(),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = TextGray,
                textAlign = TextAlign.Center,
            )
        )
    }
    if (article.commentCnt > 0) {
        Image(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = "댓글 개수",
        )
        Text(
            text = article.commentCnt.toString(),
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
    type: String,
    isProceeding: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = modifier
                .padding(1.dp)
                .width(8.dp)
                .height(8.dp)
                .clip(CircleShape)
                .background(
                    color = if (type == Constants.TOGETHER) {
                        if (!isProceeding) { Green } else { DarkGray }
                    } else {
                        if (!isProceeding) { DarkGray } else { Green }
                    }
                )
        )
        Text(
            text = if (type == Constants.TOGETHER) {
                // 함께게시판
                if (!isProceeding) "모집 중" else "모집 마감"
            } else {
                // 신고게시판
                if (!isProceeding) "진행 중" else "답변 완료"
            },
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = if (type == Constants.TOGETHER) {
                    if (!isProceeding) { Green } else { DarkGray }
                } else {
                    if (!isProceeding) { DarkGray
                    } else { Green }
                },
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun BoardPhotoView(
    modifier: Modifier = Modifier,
    photoUrl: String,
    count: Int
) {
    Box(
        modifier = modifier.size(64.dp)
    ) {
        AsyncImage(
            modifier = modifier.clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUrl)
                .build(),
            contentDescription = "업로드 사진",
            contentScale = ContentScale.Crop
        )
        // 이미지 개수에 대한 Box layout
        if (1 < count) {
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
                    text = count.toString(),
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
    CommonPostListView(type = "free", navController = rememberNavController())
}

@Preview
@Composable
fun PreviewBoardItem() {
    BoardItemView(type = "free", article = dummyArticle, navController = rememberNavController())
}