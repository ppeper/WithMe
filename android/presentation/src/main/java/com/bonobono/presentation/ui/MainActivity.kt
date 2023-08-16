package com.bonobono.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.camera2.internal.compat.quirk.StillCaptureFlashStopRepeatingQuirk
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonobono.presentation.ui.login.StartScreen
import com.bonobono.presentation.ui.theme.AndroidTheme
import com.bonobono.presentation.viewmodel.LoginViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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
                    if(loginViewModel.username.isNotBlank() && loginViewModel.password.isNotBlank()) {
                        // 자동 로그인 시킴
//                        val coroutineScope = rememberCoroutineScope()
//                        var result = ""
//                        LaunchedEffect(key1 = Unit) {
//                            result = loginViewModel.login()
//                        }
//                        // 결과에 따라 다른 화면을 렌더링
//                        if (result == "SUCCESS") {
//                            Log.d(TAG, "onCreate: $result")
//                            MainScreen()
//                        } else {
//                            Log.d(TAG, "onCreate: $result")
//                            StartScreen()
//                        }
                        MainScreen()
                    } else {
                        StartScreen()
                    }
                }
            }
        }
    }
}
