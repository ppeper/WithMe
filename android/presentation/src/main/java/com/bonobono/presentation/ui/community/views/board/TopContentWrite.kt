package com.bonobono.presentation.ui.community.views.board

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContentWrite(
    title: String = "",
    navController: NavController,
    completeButtonState: Boolean,
    onCompleteClick: () -> Unit
) {
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
            TextButton(
                onClick = { onCompleteClick() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = PrimaryBlue,
                    disabledContentColor = TextGray
                ),
                enabled = completeButtonState
            ) {
                Text(
                    text = "완료",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(700),
                    ),
                )
            }
        }
    )
}