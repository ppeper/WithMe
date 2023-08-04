package com.bonobono.presentation.ui.mypage.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.bonobono.presentation.R

@Composable
fun SettingOptions(
    option: String,
    color : Color,
    navController: NavController
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (optionStr, rightButton) = createRefs()
        Text(option,
            modifier = Modifier.constrainAs(optionStr) {
                linkTo(parent.top, parent.bottom)
                start.linkTo(parent.start)
            })
        IconButton(onClick = { // whem으로 돌리면서 조건에 따라 처리,,,?
        },
            modifier = Modifier.constrainAs(rightButton) {
                linkTo(parent.top, parent.bottom)
                end.linkTo(parent.end)
            },) {
            Icon(
                modifier = Modifier.size(20.dp, 20.dp),
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = "rightBtn"
            )
        }
    }
}

@Composable
fun MyPageButton(buttonType: String, iconName: String, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (buttonIcon, buttonTypeStr, rightButton) = createRefs()
        val context = LocalContext.current
        val drawableId = remember(iconName) {
            context.resources.getIdentifier(iconName, "drawable", context.packageName)
        }
        Icon(painter = painterResource(id = drawableId),
            contentDescription = "buttonIcon",
//            tint = WaveBlue,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .size(40.dp)
                .padding(8.dp)
                .constrainAs(buttonIcon) {
                    linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
        Text(buttonType,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(buttonTypeStr) {
                linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
                start.linkTo(buttonIcon.end, margin = 8.dp)
            })
        Icon(painter = painterResource(id = R.drawable.ic_right),
            contentDescription = "rightIcon",
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
                .constrainAs(rightButton) {
                    linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                })
    }
}

