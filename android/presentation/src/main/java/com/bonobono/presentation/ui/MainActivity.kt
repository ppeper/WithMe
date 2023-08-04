 package com.bonobono.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bonobono.presentation.ui.theme.AndroidTheme
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

 @Composable
 fun Test() {

 }
