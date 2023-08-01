package com.bonobono.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.chatting.MainChattingScreen
import com.bonobono.presentation.ui.community.MainCommunityScreen
import com.bonobono.presentation.ui.component.FloatingButton
import com.bonobono.presentation.ui.main.EncyclopediaScreen
import com.bonobono.presentation.ui.main.MainHomeScreen
import com.bonobono.presentation.ui.main.MissionScreen
import com.bonobono.presentation.ui.main.NoticeScreen
import com.bonobono.presentation.ui.map.CameraScreen
import com.bonobono.presentation.ui.map.MainMapScreen
import com.bonobono.presentation.ui.mypage.MainMyPageScreen
import com.bonobono.presentation.utils.NavigationUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if(MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController = navController, currentRoute = currentRoute)
            }
        },
        floatingActionButton =  {
            if(currentRoute == NavigationRouteName.MAIN_HOME) {
                MainFloatingActionButtons(navController = navController, currentRoute = currentRoute)
            }
        }
    ) {
        it
        MainNavigationScreen(navController = navController, scaffoldState = scaffoldState, it)
    }
}

@Composable
fun MainFloatingActionButtons(navController: NavHostController, currentRoute: String?) {
    val fabItems = listOf(
        MainFab.MISSION,
        MainFab.ENCYCLOPEDIA,
        MainFab.NOTICE,
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
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Map,
        MainNav.Community,
        MainNav.Chatting,
        MainNav.MyPage,
    )

    BottomNavigation {
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, item.route) },
                selected = currentRoute == item.route, onClick = {
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
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = MainNav.Home.route, modifier = Modifier.padding(paddingValues)) {
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
            MainCommunityScreen(navController)
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
    }
}

@Preview
@Composable
fun Preview() {
    MainScreen()
}
