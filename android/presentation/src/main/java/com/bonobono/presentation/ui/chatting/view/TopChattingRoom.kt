package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopChattingRoom(
    title: String = "",
    navController: NavController,
    onExitClick: () -> Unit,
    onReportClick: () -> Unit
) {
    var dropdownMenuExpanded by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        modifier = Modifier.graphicsLayer {
            shadowElevation = 5f
        },
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "뒤로가기"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { dropdownMenuExpanded = true },
            ) {
                Icon(

                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "더보기"
                )
            }
            DropdownMenu(
                modifier = Modifier.background(White),
                expanded = dropdownMenuExpanded,
                onDismissRequest = { dropdownMenuExpanded = false },
            ) {
                DropdownMenuItem(text = { Text(text = "채팅방 나가기") }, onClick = onExitClick)
                DropdownMenuItem(text = { Text(text = "신고하기") }, onClick = onReportClick)
            }
        }
    )
}