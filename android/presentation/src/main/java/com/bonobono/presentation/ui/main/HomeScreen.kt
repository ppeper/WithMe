package com.bonobono.presentation.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.component.AnimatedCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(navController: NavController) {
    AnimatedCard()
}

@Preview
@Composable
fun Preview() {
    MainHomeScreen(navController = rememberNavController())
}

