package com.bonobono.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.FindIdNav
import com.bonobono.presentation.ui.FindPasswordNav
import com.bonobono.presentation.ui.JoinNav
import com.bonobono.presentation.ui.LoginNav

@Composable
fun StartScreen() {
    val loginNavController = rememberNavController()
    LoginNavigationScreen(navController = loginNavController)

}

@Composable
fun LoginNavigationScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = LoginNav.route
    ) {
        composable(
            route = LoginNav.route,
            deepLinks = LoginNav.deepLinks
        ) {
            LoginScreen(navController)
        }
        composable(
            route = JoinNav.route,
            deepLinks = JoinNav.deepLinks
        ) {
            JoinScreen(navController)
        }
        composable(
            route = FindIdNav.route,
            deepLinks = FindIdNav.deepLinks
        ) {
            FindIDScreen(navController)
        }
        composable(
            route = FindPasswordNav.route,
            deepLinks = FindPasswordNav.deepLinks
        ) {
            FindPWDScreen(navController)
        }
    }
}
