package com.bonobono.presentation.ui.community

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.bonobono.presentation.ui.common.CheckCountDialog
import com.bonobono.presentation.ui.community.util.routeMapper
import com.bonobono.presentation.ui.community.util.textMapper
import com.bonobono.presentation.ui.community.views.board.BoardWriteBottomView
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
import com.bonobono.presentation.viewmodel.PhotoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BoardWriteScreen(
    modifier: Modifier = Modifier,
    type: String,
    navController: NavController,
    photoViewModel: PhotoViewModel = hiltViewModel(),
    communityViewModel: CommunityViewModel = hiltViewModel()
) {
    val route = navController.currentDestination?.route ?: CommunityFab.FREE.route
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var titleTextState by rememberSaveable { mutableStateOf("") }
    var contentTextState by rememberSaveable { mutableStateOf("") }
    val previousCount = photoViewModel.selectedPhoto.size
    val writeArticleState by communityViewModel.writeArticleState.collectAsStateWithLifecycle()
    val mapState by communityViewModel.mapState

    val galleryPermission =
        rememberMultiplePermissionsState(permissions = GALLERY_PERMISSIONS)

    val locationPermission =
        rememberMultiplePermissionsState(permissions = LOCATION_PERMISSIONS)

    Scaffold(
        topBar = {
            TopContentWrite(
                title = "글 작성",
                navController = navController,
                completeButtonState = titleTextState.isNotBlank() && contentTextState.isNotBlank(),
                onCompleteClick = { /* TODO("서버로 게시글 등록") */
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
                            latitude = it.latitude,
                            longitude = it.longitude
                        )
                    }
                    val photoList = photoViewModel.selectedPhoto.mapNotNull {
                        Converter.getRealPathFromUriOrNull(context, Uri.parse(it.url))
                    }.ifEmpty { null }
                    Log.d("TEST", "BoardWriteScreen(photoList): $photoList")
                    communityViewModel.writeArticle(type, photoList, article = article)
                    Log.d("TEST", "BoardWriteScreen(article): $article")
                    when (writeArticleState) {
                        is NetworkResult.Success -> {
                            navController.popBackStack()
                        }
                        is NetworkResult.Loading -> {
                            Log.d("TEST", "글쓰기: 로딩중~~~~~~~~~~~")
                        }
                        is NetworkResult.Error -> {
                            Log.d("TEST", "글쓰기: 오류~~~~~~~~~~~")
                        }
                    }
                }
            )
        },
        bottomBar = {
            BoardWriteBottomView(
                route = route,
                onPhotoClick = {
                    galleryPermission.launchMultiplePermissionRequest()
                    if (galleryPermission.allPermissionsGranted) {
                        if (previousCount == 10) {
                            showDialog = true
                        } else navController.navigate(routeMapper(navController))
                    } else {
//                        PermissionDialog(content = "사진권한이 필요한 작업입니다. 이동하시겠습니까?") {
//                            val intent = Intent().apply {
//                                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                                data = Uri.fromParts("package", context.packageName, null)
//                            }
//                            context.startActivity(intent)
//                        }
                    }
                },
                onMapClick = {
                    locationPermission.launchMultiplePermissionRequest()
                    if (galleryPermission.allPermissionsGranted) {
                        navController.navigate(NavigationRouteName.REPORT_MAP)
                    } else {
                    }
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
                        modifier = modifier.fillMaxWidth(),
                        value = titleTextState,
                        onValueChange = { titleTextState = it },
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
                        onValueChange = { contentTextState = it },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Black_100,
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                    } else  {
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
    BoardWriteScreen(type = "free", navController = rememberNavController())
}