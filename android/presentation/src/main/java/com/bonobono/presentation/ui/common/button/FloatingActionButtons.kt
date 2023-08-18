package com.bonobono.presentation.ui.common.button

import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.MainFab
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.NavigationUtils


@Composable
fun HomeFloatingActionButton(
    navController: NavController,
    item: MainFab,
) {
    FloatingActionButton(
        containerColor = PrimaryBlue,
        contentColor = White,
        onClick = {
            Log.d("TEST", "HomeFloatingActionButton: ${navController.currentBackStack.value}")
            navController.navigate(item.route)
        }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
        )
    }
}

@Composable
fun CommunityFloatingActionButton(
    navController: NavController,
    item: CommunityFab
) {
    FloatingActionButton(
        containerColor = PrimaryBlue,
        contentColor = White,
        onClick = {
            navController.navigate(item.route)
        }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title
        )
    }
}