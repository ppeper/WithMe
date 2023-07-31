package com.bonobono.presentation.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun FloatingButton(icon: ImageVector, title: String, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.clip(CircleShape),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
        )
    }
}