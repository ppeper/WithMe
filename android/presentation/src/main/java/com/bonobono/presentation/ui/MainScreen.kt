package com.bonobono.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_FREE
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_REPORT
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_WITH
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_COMMUNITY
import com.bonobono.presentation.ui.chatting.MainChattingScreen
import com.bonobono.presentation.ui.common.topbar.SharedTopAppBar
import com.bonobono.presentation.ui.common.topbar.rememberAppBarState
import com.bonobono.presentation.ui.community.CommunityScreen
import com.bonobono.presentation.ui.community.BoardWriteScreen
import com.bonobono.presentation.ui.community.views.CommonPostListView
import com.bonobono.presentation.ui.community.views.DummyData
import com.bonobono.presentation.ui.community.GalleryScreen
import com.bonobono.presentation.ui.component.FloatingButton
import com.bonobono.presentation.ui.main.EncyclopediaScreen
import com.bonobono.presentation.ui.main.MainHomeScreen
import com.bonobono.presentation.ui.main.MissionScreen
import com.bonobono.presentation.ui.main.NoticeScreen
import com.bonobono.presentation.ui.map.CameraScreen
import com.bonobono.presentation.ui.map.MainMapScreen
import com.bonobono.presentation.ui.mypage.MainMyPageScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.NavigationUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val appBarState = rememberAppBarState(navController = navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
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
                    MainFloatingActionButtons(
                        navController = navController,
                        currentRoute = currentRoute
                    )
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
                NavigationRouteName.COMMUNITY_REPORT-> {
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
            scaffoldState = scaffoldState
        )
    }
}

@Composable
fun MainFloatingActionButtons(navController: NavHostController, currentRoute: String?) {
    val fabItems = listOf(
        MainFab.MISSION,
        MainFab.ENCYCLOPEDIA,
        MainFab.NOTICE
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        fabItems.forEach { item ->
            FloatingButton(icon = item.icon, title = item.title) {
                NavigationUtils.navigate(
                    navController, item.route,
                    navController.graph.startDestinationRoute
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CommunityFloatingActionButton(
    navController: NavHostController,
    item: CommunityFab
) {
    FloatingActionButton(
        containerColor = PrimaryBlue,
        contentColor = White,
        onClick = {
            NavigationUtils.navigate(
                navController, item.route,
                navController.graph.startDestinationRoute
            )
        }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title
        )
    }
}

// 커뮤니티에서의 bottom Navigation Route
fun isCommunityRoute(
    route: String
): String {
    return if (route in listOf(COMMUNITY_FREE, COMMUNITY_WITH, COMMUNITY_REPORT)) {
        MAIN_COMMUNITY
    } else {
        route
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Map,
        MainNav.Community,
        MainNav.Chatting,
        MainNav.MyPage,
    )

    NavigationBar(
        modifier = Modifier.graphicsLayer {
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            )
            clip = true
            shadowElevation = 20f
        },
        containerColor = White,
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
                selected = currentRoute == item.route || currentRoute?.let { isCommunityRoute(it) } == item.route, onClick = {
                    NavigationUtils.navigate(
                        navController, item.route,
                        navController.graph.startDestinationRoute
                    )
                })
        }
    }
}

@Composable
fun MainNavigationScreen(
    innerPaddings: PaddingValues,
    navController: NavHostController,
    scaffoldState: ScaffoldState
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
            MainHomeScreen(navController)
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
        composable(
            route = MainNav.Chatting.route,
            deepLinks = MainNav.Chatting.deepLinks
        ) {
            MainChattingScreen(navController)
        }
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
            MissionScreen(navController)
        }
        composable(
            route = EncyclopediaNav.route,
            deepLinks = EncyclopediaNav.deepLinks
        ) {
            EncyclopediaScreen()
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
            CameraScreen()
        }
        /// TODO("커뮤니티 글쓰기 TEST")
        composable(
            route = NavigationRouteName.GALLERY
        ) {
            GalleryScreen(navController = navController)
        }
        composable(
            route = CommunityFab.FREE.route
        ) {
            BoardWriteScreen(navController = navController)
        }
        composable(
            route = CommunityFab.WITH.route
        ) {
            BoardWriteScreen(navController = navController)
        }
        composable(
            route = CommunityFab.REPORT.route
        ) {
            BoardWriteScreen(navController = navController)
        }
        composable(
            route = CommunityFreeNav.route,
            deepLinks = CommunityFreeNav.deepLinks
        ) {
            CommonPostListView(boardList = DummyData.boardList, navController = navController)
        }
        composable(
            route = CommunityWithNav.route,
            deepLinks = CommunityWithNav.deepLinks
        ) {
            CommonPostListView(boardList = DummyData.boardList, navController = navController)
        }
        composable(
            route = CommunityReportNav.route,
            deepLinks = CommunityReportNav.deepLinks
        ) {
            CommonPostListView(boardList = DummyData.boardList, navController = navController)
        }
    }
}

@Preview
@Composable
fun Preview() {
    MainScreen()
}
