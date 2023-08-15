package com.bonobono.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.login.StartScreen
import com.bonobono.presentation.ui.map.CampaignWriteScreen
import com.bonobono.presentation.ui.onboarding.OnBoardingScreen
import com.bonobono.presentation.ui.theme.AndroidTheme
import com.bonobono.presentation.viewmodel.LoginViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "싸피"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val loginViewModel : LoginViewModel = hiltViewModel()
                    loginViewModel.getLoginInfo()
//                    if(loginViewModel.username.isNotBlank() && loginViewModel.password.isNotBlank()) {
//                        // 자동 로그인 시킴
//                        MainScreen()
//                    } else {
//                        StartScreen()
//                    }
                    CampaignWriteScreen(navController = rememberNavController())
//                    MainScreen()
//                    StartScreen()
                }
            }
        }
    }
}
