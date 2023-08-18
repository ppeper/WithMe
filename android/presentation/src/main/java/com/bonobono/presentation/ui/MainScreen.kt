package com.bonobono.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_FREE
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_GRAPH
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_REPORT
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_WITH
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_COMMUNITY
import com.bonobono.presentation.ui.common.button.CommunityFloatingActionButton
import com.bonobono.presentation.ui.common.button.HomeFloatingActionButton
import com.bonobono.presentation.ui.common.topbar.SharedTopAppBar
import com.bonobono.presentation.ui.common.topbar.rememberAppBarState
import com.bonobono.presentation.ui.community.BoardDetailScreen
import com.bonobono.presentation.ui.community.BoardWriteScreen
import com.bonobono.presentation.ui.community.CommunityScreen
import com.bonobono.presentation.ui.community.GalleryScreen
import com.bonobono.presentation.ui.community.views.board.CommonPostListView
import com.bonobono.presentation.ui.community.views.board.SearchView
import com.bonobono.presentation.ui.community.views.link.WebView
import com.bonobono.presentation.ui.community.views.map.ReportMapView
import com.bonobono.presentation.ui.login.LoginNavigationScreen
import com.bonobono.presentation.ui.login.LoginScreen
import com.bonobono.presentation.ui.main.MainHomeScreen
import com.bonobono.presentation.ui.main.ecyclopedia.EncyclopediaScreen
import com.bonobono.presentation.ui.main.mission.GameScreen
import com.bonobono.presentation.ui.main.mission.MissionScreen
import com.bonobono.presentation.ui.main.mission.QuizScreen
import com.bonobono.presentation.ui.main.notice.NoticeScreen
import com.bonobono.presentation.ui.map.ARMapScreen
import com.bonobono.presentation.ui.map.CameraScreen
import com.bonobono.presentation.ui.map.CampaignWriteScreen
import com.bonobono.presentation.ui.map.MainMapScreen
import com.bonobono.presentation.ui.mypage.MainMyPageScreen
import com.bonobono.presentation.ui.mypage.PointStoreScreen
import com.bonobono.presentation.ui.mypage.ProfileEditScreen
import com.bonobono.presentation.ui.mypage.SettingScreen
import com.bonobono.presentation.ui.notification.NotificationScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.NavigationUtils
import com.bonobono.presentation.viewmodel.SharedLocalViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun MainScreen(
    type: String? = null,
    articleId: String? = null
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val appBarState = rememberAppBarState(navController = navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val localViewModel: SharedLocalViewModel = hiltViewModel()
    val role = localViewModel.getRole("role")
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            if (appBarState.isVisible) {
                SharedTopAppBar(appBarState = appBarState)
            }
        },
        bottomBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController = navController, currentRoute = currentRoute)
            }
        },
        floatingActionButton = {
            when (currentRoute) {
                NavigationRouteName.MAIN_HOME -> {
                    MainFloatingActionButtons(navController)
                }

                NavigationRouteName.MAIN_MAP -> {
                    if(role == Constants.ADMIN_ROLE) {
                        CommunityFloatingActionButton(
                            navController = navController,
                            item = CommunityFab.MAP
                        )
                    }
                }

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
        MainNavigationScreen(
            innerPaddings = it,
            navController = navController,
            showSnackBar = { message ->
                scope.launch {
                    snackBarHostState.showSnackbar(message)
                }
            }
        )
        LaunchedEffect(Unit) {
            if (type != null && articleId != null) {
                Log.d("TEST", "MainScreen: PendingIntent 호출")
                navController.navigate("${BoardDetailNav.route}/${type.lowercase(Locale.getDefault())}/$articleId")
            }
        }
    }
}

@Composable
fun MainFloatingActionButtons(navController: NavController) {
    val fabItems = listOf(
        MainFab.MISSION,
        MainFab.ENCYCLOPEDIA,
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        fabItems.forEach { item ->
            HomeFloatingActionButton(navController = navController, item = item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Map,
        MainNav.Community,
//        MainNav.Chatting,
        MainNav.MyPage,
    )

    val permissions = listOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val multiplePermissionsState = rememberMultiplePermissionsState(permissions = permissions) {
        val result = it.values.reduce { acc, granted -> acc || granted }
        if (result) {
            val item = bottomNavigationItems.find { it.route == NavigationRouteName.MAIN_MAP }
            if (item != null) {
                NavigationUtils.navigate(
                    navController, item.route,
                    navController.graph.startDestinationRoute
                )
            }
        } else {

        }
    }

    NavigationBar(
        tonalElevation = 0.dp,
        modifier = Modifier.graphicsLayer {
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            )
            clip = true
            shadowElevation = 20f
        },
    ) {
        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextGray,
                    unselectedTextColor = TextGray,
                    indicatorColor = White
                ),
                label = { Text(text = item.title) },
                icon = { Icon(painter = painterResource(id = item.icon), item.route) },
                selected = currentRoute == item.route || (item.route == currentRoute?.let {
                    parseCommunityRoute(
                        it
                    )
                }),
                onClick = {
                    if (item.route == NavigationRouteName.MAIN_MAP) {
                        multiplePermissionsState.launchMultiplePermissionRequest()
                    } else {
                        NavigationUtils.navigate(
                            navController, item.route,
                            navController.graph.startDestinationRoute
                        )
                    }
                })
        }
    }
}

fun parseCommunityRoute(route: String): String {
    return if (route in listOf(
            COMMUNITY_FREE,
            COMMUNITY_WITH,
            COMMUNITY_REPORT
        )
    ) MAIN_COMMUNITY else route
}


@Composable
fun MainNavigationScreen(
    innerPaddings: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.padding(innerPaddings),
        navController = navController,
        startDestination = MainNav.Home.route
    ) {
        composable(
            route = MainNav.Home.route,
            deepLinks = MainNav.Home.deepLinks
        ) {
            MainHomeScreen()
        }
        composable(
            route = MainNav.Map.route,
            deepLinks = MainNav.Map.deepLinks
        ) {
            MainMapScreen(navController)
        }
        composable(
            route = MainNav.Community.route,
            deepLinks = MainNav.Community.deepLinks
        ) {
            CommunityScreen(navController)
        }
//        composable(
//            route = MainNav.Chatting.route,
//            deepLinks = MainNav.Chatting.deepLinks
//        ) {
//            MainChattingScreen(navController)
//        }
        composable(
            route = MainNav.MyPage.route,
            deepLinks = MainNav.MyPage.deepLinks
        ) {
            MainMyPageScreen(navController)
        }
        composable(
            route = MissionNav.route,
            deepLinks = MissionNav.deepLinks
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.MAIN_HOME)
            }
            MissionScreen(navController, characterViewModel = hiltViewModel(parentEntry))
        }
        composable(
            route = EncyclopediaNav.route,
            deepLinks = EncyclopediaNav.deepLinks
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.MAIN_HOME)
            }
            EncyclopediaScreen(
                navController = navController,
                characterViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = NoticeNav.route,
            deepLinks = NoticeNav.deepLinks
        ) {
            NoticeScreen()
        }
        composable(
            route = CameraNav.route,
            deepLinks = CameraNav.deepLinks
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.MAIN_MAP)
            }
            CameraScreen(mapViewModel = hiltViewModel(parentEntry), navController = navController)
        }
        composable(
            route = NavigationRouteName.GALLERY
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.COMMUNITY_POST)
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = NavigationRouteName.GALLERY_WITH
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.COMMUNITY_POST_WITH)
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = NavigationRouteName.GALLERY_REPORT
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.COMMUNITY_POST_REPORT)
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = NavigationRouteName.GALLERY_UPDATE
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(navController.currentDestination?.route.toString())
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = NavigationRouteName.GALLERY_UPDATE_WITH
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(navController.currentDestination?.route.toString())
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = NavigationRouteName.GALLERY_UPDATE_REPORT
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(navController.currentDestination?.route.toString())
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = CommunityFab.FREE.route
        ) {
            BoardWriteScreen(type = COMMUNITY_FREE, navController = navController) {
                showSnackBar("자유 게시글이 등록되었습니다.")
            }
        }
        composable(
            route = CommunityFab.WITH.route
        ) {
            BoardWriteScreen(type = COMMUNITY_WITH, navController = navController) {
                showSnackBar("함께 게시글이 등록되었습니다.")
            }
        }
        composable(
            route = CommunityFab.REPORT.route
        ) {
            BoardWriteScreen(type = COMMUNITY_REPORT, navController = navController) {
                showSnackBar("신고 게시글이 등록되었습니다.")
            }
        }
        composable(
            route = CommunityFab.MAP.route
        ) {
            CampaignWriteScreen(navController = navController)
        }
        communityNavigation(navController = navController)

        composable(
            route = "${BoardDetailNav.route}/{type}/{articleId}"
        ) {
            val type = it.arguments?.getString("type")
            val articleId = it.arguments?.getString("articleId")
            if (articleId != null && type != null) {
                BoardDetailScreen(
                    type = type,
                    articleId = articleId.toLong(),
                    navController = navController
                ) {
                    showSnackBar("삭제 되었습니다.")
                }
            }
        }

        composable(
            route = NavigationRouteName.COMMUNITY_NOTIFICATION
        ) {
            NotificationScreen(navController = navController)
        }


        composable(
            route = "${NavigationRouteName.COMMUNITY_SEARCH}/{type}"
        ) {
            val type = it.arguments?.getString("type")
            if (type != null) {
                SearchView(
                    type = type,
                    navController = navController
                )
            }
        }
        composable(
            route = "${NavigationRouteName.LINK_WEB_VIEW}/{url}"
        ) {
            val linkUrl = it.arguments?.getString("url")
            linkUrl?.let { url ->
                WebView(url = url)
            }
        }
        composable(
            route = NavigationRouteName.REPORT_MAP
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.COMMUNITY_POST_REPORT)
            }
            ReportMapView(
                navController = navController,
                communityViewModel = hiltViewModel(parentEntry)
            )
        }

        composable(
            route = "${NavigationRouteName.REPORT_MAP}/{type}/{articleId}"
        ) {
            val type = it.arguments?.getString("type")
            val articleId = it.arguments?.getString("articleId")
            if (articleId != null && type != null) {
                ReportMapView(navController = navController)
            }
        }

        // 마이페이지
        composable(
            route = SettingNav.route,
            deepLinks = SettingNav.deepLinks
        ) {
            SettingScreen(navController = navController)
        }
        composable(
            route = PointStoreNav.route,
            deepLinks = PointStoreNav.deepLinks
        ) {
            PointStoreScreen(navController = navController)
        }
        composable(
            route = ProfileEditNav.route,
            deepLinks = ProfileEditNav.deepLinks
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.MAIN_MY_PAGE)
            }
            ProfileEditScreen(
                navController = navController,
                myPageViewModel = hiltViewModel(parentEntry)
            )
        }
        composable(
            route = "${QuizNav.route}/{type}",
            deepLinks = QuizNav.deepLinks
        ) {
            val type = it.arguments?.getString("type")
            if (type != null) {
                QuizScreen(type, navController = navController)
            }
        }
        composable(
            route = GameNav.route,
            deepLinks = QuizNav.deepLinks
        ) {
            GameScreen(navController)
        }
        composable(
            route = ARMapNav.route,
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.MAIN_MAP)
            }
            ARMapScreen(navController = navController, hiltViewModel(parentEntry))
        }

//        composable(
//            route = "${ChattingEditNav.route}/{nickname}",
//            deepLinks = ChattingEditNav.deepLinks
//        ) {
//            val nickname = it.arguments?.getString("nickname")
//            if(nickname != null) {
//                ChattingRoomScreen(navController = navController, roomTitle = nickname)
//            }
//        }

//        composable(
//            route = NavigationRouteName.CHATTING_GALLERY
//        ) {
//            val parentEntry = remember(it) {
//                navController.getBackStackEntry(
//                    "${ChattingEditNav.route}/${
//                        it.arguments?.getString(
//                            "nickname"
//                        )
//                    }"
//                )
//            }
//            GalleryScreen(
//                navController = navController,
//                photoViewModel = hiltViewModel(parentEntry)
//            )
//        }
//        composable(
//            route = NavigationRouteName.CHATTING_GALLERY
//        ) {
//            val parentEntry = remember(it) {
//                navController.getBackStackEntry("${ChattingEditNav.route}/${it.arguments?.getString("nickname")}")
//            }
//            GalleryScreen(
//                navController = navController,
//                photoViewModel = hiltViewModel(parentEntry)
//            )
//        }

        composable(
            route = NavigationRouteName.PROFILE_EDIT_GALLERY
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NavigationRouteName.PROFILE_EDIT)
            }
            GalleryScreen(
                navController = navController,
                photoViewModel = hiltViewModel(parentEntry)
            )
        }

        composable("backToLogin"
        ) {
            val loginNavController = rememberNavController()
            LoginNavigationScreen(navController = loginNavController)
        }

    }
}

fun NavGraphBuilder.communityNavigation(
    navController: NavController
) {
    navigation(startDestination = MAIN_COMMUNITY, route = COMMUNITY_GRAPH) {
        composable(
            route = CommunityFreeNav.route,
            deepLinks = CommunityFreeNav.deepLinks
        ) {
            CommonPostListView(type = CommunityFreeNav.route, navController = navController)
        }
        composable(
            route = CommunityWithNav.route,
            deepLinks = CommunityWithNav.deepLinks
        ) {
            CommonPostListView(type = CommunityWithNav.route, navController = navController)
        }
        composable(
            route = CommunityReportNav.route,
            deepLinks = CommunityReportNav.deepLinks
        ) {
            CommonPostListView(type = CommunityReportNav.route, navController = navController)
        }
        composable(
            route = LoginNav.route,
            deepLinks = LoginNav.deepLinks
        ) {
            LoginScreen(navController = rememberNavController())
        }
    }

}


@Preview
@Composable
fun Preview() {
    MainScreen()
}
