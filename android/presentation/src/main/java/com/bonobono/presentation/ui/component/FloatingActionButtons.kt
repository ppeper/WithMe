package com.bonobono.presentation.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.NavigationUtils


@Composable
fun HomeFloatingActionButton(icon: Int, title: String, onClick: () -> Unit) {
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

@Composable
fun CommunityFloatingActionButton(
    navController: NavHostController,
    item: CommunityFab
) {
    FloatingActionButton(
        containerColor = PrimaryBlue,
        contentColor = White,
        shape = CircleShape,
        onClick = {
            NavigationUtils.navigate(
                navController, item.route,
                navController.graph.startDestinationRoute
            )
        }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title
        )
    }
}