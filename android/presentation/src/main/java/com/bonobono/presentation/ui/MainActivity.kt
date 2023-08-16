package com.bonobono.presentation.ui

import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonobono.presentation.ui.login.StartScreen
import com.bonobono.presentation.ui.theme.AndroidTheme
import com.bonobono.presentation.utils.Constants.CHANNEL_ID
import com.bonobono.presentation.utils.Constants.CHANNEL_NAME
import com.bonobono.presentation.utils.createNotificationChannel
import com.bonobono.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "싸피"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val notificationManager: NotificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    private val type by lazy { intent.getStringExtra("type") }
    private val articleId by lazy { intent.getStringExtra("articleId") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(notificationManager, CHANNEL_ID, CHANNEL_NAME)

        setContent {
            AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val loginViewModel : LoginViewModel = hiltViewModel()
                    loginViewModel.getLoginInfo()
                    if(loginViewModel.username.isNotBlank() && loginViewModel.password.isNotBlank()) {
                        // 자동 로그인 시킴
                        MainScreen(type, articleId)
                    } else {
                        StartScreen()
                    }
//                    MainScreen()
//                    StartScreen()
                }
            }
        }
    }
}
