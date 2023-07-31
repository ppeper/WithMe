package com.bonobono.presentation.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(navController: NavController) {
    Text("home")
}

@Preview
@Composable
fun Preview() {
    MainHomeScreen(navController = rememberNavController())
}

