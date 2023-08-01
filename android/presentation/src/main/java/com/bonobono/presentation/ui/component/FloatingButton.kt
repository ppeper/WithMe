package com.bonobono.presentation.ui.component

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.theme.White


@Composable
fun FloatingButton(icon: Int, title: String, onClick: () -> Unit) {
    FloatingActionButton(
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
        containerColor = White,
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
        )
    }
}