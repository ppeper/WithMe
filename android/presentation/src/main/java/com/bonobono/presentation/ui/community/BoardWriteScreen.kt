package com.bonobono.presentation.ui.community

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_REPORT
import com.bonobono.presentation.ui.common.CheckCountDialog
import com.bonobono.presentation.ui.common.LoadingView
import com.bonobono.presentation.ui.community.util.routeMapper
import com.bonobono.presentation.ui.community.util.textMapper
import com.bonobono.presentation.ui.community.views.board.BoardWriteBottomView
import com.bonobono.presentation.ui.community.views.board.ReportDropDown
import com.bonobono.presentation.ui.community.views.board.TopContentWrite
import com.bonobono.presentation.ui.community.views.gallery.PhotoSelectedListView
import com.bonobono.presentation.ui.community.views.link.LinkView
import com.bonobono.presentation.ui.community.views.map.SelectedMapView
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.utils.Converter
import com.bonobono.presentation.utils.PermissionUtils.GALLERY_PERMISSIONS
import com.bonobono.presentation.utils.PermissionUtils.LOCATION_PERMISSIONS
import com.bonobono.presentation.viewmodel.CommunityViewModel
import com.bonobono.presentation.viewmodel.MapViewModel
import com.bonobono.presentation.viewmodel.PhotoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

const val MAX_TITLE_LENGTH = 20
const val MAX_CONTENT_LENGTH = 1000

@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class)
@Composable
fun BoardWriteScreen(
    modifier: Modifier = Modifier,
    type: String,
    navController: NavController,
    photoViewModel: PhotoViewModel = hiltViewModel(),
    communityViewModel: CommunityViewModel = hiltViewModel(),
    mapViewModel: MapViewModel = hiltViewModel(),
    showSnackBar: () -> Unit
) {
    val route = navController.currentDestination?.route ?: CommunityFab.FREE.route
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var showDialog by remember { mutableStateOf(false) }
    var titleTextState by rememberSaveable { mutableStateOf("") }
    var contentTextState by rememberSaveable { mutableStateOf("") }
    val selectedOcean by mapViewModel.selectedLocation.collectAsStateWithLifecycle()

    val previousCount = photoViewModel.selectedPhoto.size
    val writeArticleState by communityViewModel.writeArticleState.collectAsStateWithLifecycle()
    // 신고 게시판
    val mapState by communityViewModel.mapState
    val locations by mapViewModel.locations.collectAsState()

    LaunchedEffect(Unit) {
        mapViewModel.getLocations()
    }
    val galleryPermission =
        rememberMultiplePermissionsState(permissions = GALLERY_PERMISSIONS)

    val locationPermission =
        rememberMultiplePermissionsState(permissions = LOCATION_PERMISSIONS)
    LaunchedEffect(writeArticleState) {
        when (writeArticleState) {
            is NetworkResult.Success -> {
                Log.d("TEST", "BoardWriteScreen: 뒤로가기")
                navController.popBackStack()
                showSnackBar()
            }
            is NetworkResult.Loading -> {
                Log.d("TEST", "글쓰기: 로딩중~~~~~~~~~~~")
            }
            is NetworkResult.Error -> {
                Log.d("TEST", "글쓰기: 오류~~~~~~~~~~~ $")
            }
        }
    }
    Scaffold(
        topBar = {
            TopContentWrite(
                title = "글 작성",
                navController = navController,
                completeButtonState =
                // 신고 게시판은 LocationId 값도 필요
                if (type == COMMUNITY_REPORT) {
                    titleTextState.isNotBlank() && contentTextState.isNotBlank() && selectedOcean != null && mapState != null
                } else {
                    titleTextState.isNotBlank() && contentTextState.isNotBlank()
                }
                ,
                onCompleteClick = {
                    val link = communityViewModel.link.value
                    // 갤러리 이미지 목록 Real Path로 변경
                    var article = Article(
                        title = titleTextState,
                        content = contentTextState,
                    )
                    if (link.imageUrl.isNotBlank()) {
                        article = article.copy(
                            url = link.url,
                            urlTitle = link.urlTitle
                        )
                    }
                    mapState?.let {
                        article = article.copy(
                            locationId = selectedOcean?.id,
                            latitude = it.latitude,
                            longitude = it.longitude
                        )
                    }
                    val photoList = photoViewModel.selectedPhoto.mapNotNull {
                        Converter.getRealPathFromUriOrNull(context, Uri.parse(it.url))
                    }.ifEmpty { null }
                    Log.d("TEST", "BoardWriteScreen(article): $article")
                    communityViewModel.writeArticle(type, photoList, article = article)
                }
            )
        },
        bottomBar = {
            BoardWriteBottomView(
                route = route,
                onPhotoClick = {
                    galleryPermission.launchMultiplePermissionRequest()
                    if (previousCount == 10) {
                        showDialog = true
                    } else {
                        navController.navigate(routeMapper(navController))
                    }
                },
                onMapClick = {
                    locationPermission.launchMultiplePermissionRequest()
                    navController.navigate(NavigationRouteName.REPORT_MAP)
                }
            )
        }
    ) { innerPaddings ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .pointerInput(Unit) {
                        detectTapGestures {
                            keyboardController?.hide()
                        }
                    }
            ) {
                if (showDialog) {
                    CheckCountDialog(title = "사진 선택", count = 10) {
                        showDialog = !showDialog
                    }
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    BasicTextField(
                        modifier = modifier.fillMaxWidth()
                            .wrapContentHeight(),
                        value = titleTextState,
                        onValueChange = {
                            if (it.length <= MAX_TITLE_LENGTH) titleTextState = it
                        },
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(700),
                            color = Black_100,
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        decorationBox = { innerTextField ->
                            if (titleTextState.isEmpty()) {
                                Text(
                                    text = "제목 추가",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        lineHeight = 18.sp,
                                        fontWeight = FontWeight(700),
                                        color = TextGray,
                                    ),
                                )
                            }
                            innerTextField()
                        }
                    )
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = contentTextState,
                        onValueChange = {
                            if (it.length <= MAX_CONTENT_LENGTH) contentTextState = it
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Black_100,
                        ),
                        decorationBox = { innerTextField ->
                            if (contentTextState.isEmpty()) {
                                Text(
                                    text = textMapper(navController),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = TextGray,
                                    ),
                                )
                            }
                            innerTextField()
                        }
                    )
                    PhotoSelectedListView(
                        photoViewModel = photoViewModel
                    )
                    // 커뮤니티 별 추가 UI
                    if (route == CommunityFab.WITH.route) {
                        LinkView()
                    }
                    if (route == CommunityFab.REPORT.route) {
                        // 현재 해변 선택
                        ReportDropDown(locations, mapViewModel = mapViewModel)
                        mapState?.let {
                            SelectedMapView(mapState = it) {
                                communityViewModel.removeMapPosition()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    BoardWriteScreen(type = "free", navController = rememberNavController()) {}
}