package com.bonobono.presentation.ui.component

import androidx.compose.material.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun FloatingButton(icon: ImageVector, title: String, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = title)
    }
}