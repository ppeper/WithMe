package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.theme.LightGray_50
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SettingScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        SettingScreen.buttons
            .onEach { button ->
                when (button) {
                    SettingScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        SettingTitle(title = "계정 관리")
        SettingOptions(option = "권한설정")
        SettingOptions(option = "비밀번호")
        SettingOptions(option = "회원탈퇴")
        SettingDivider()
        SettingTitle(title = "앱 정보")
        SettingVersionOption(version = "1.00")
        SettingOptions(option = "라이선스")
        SettingDivider()
    }
}

@Composable
fun SettingTitle(title: String) {
    Text(title,
        modifier = Modifier.fillMaxWidth())
}

@Composable
fun SettingOptions(option: String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (optionStr, rightButton) = createRefs()
        Text(option,
            modifier = Modifier.constrainAs(optionStr) {
                linkTo(parent.top, parent.bottom, topMargin = 4.dp, bottomMargin = 4.dp)
                start.linkTo(parent.start, margin = 16.dp)
            })
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(rightButton) {
                linkTo(parent.top, parent.bottom, topMargin = 4.dp, bottomMargin = 4.dp)
                end.linkTo(parent.end, margin = 16.dp)
            },) {
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = "rightBtn"
            )
        }
    }
}

@Composable
fun SettingVersionOption(version:String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (versionStr, versionInfoStr) = createRefs()
        Text("버전정보",
            modifier = Modifier.constrainAs(versionStr) {
                linkTo(parent.top, parent.bottom, topMargin = 4.dp, bottomMargin = 4.dp)
                start.linkTo(parent.start, margin = 16.dp)
            })
        Text(version,
            modifier = Modifier.constrainAs(versionInfoStr) {
                linkTo(parent.top, parent.bottom, topMargin = 4.dp, bottomMargin = 4.dp)
                end.linkTo(parent.end, margin = 16.dp)
            })
    }
}

@Composable
fun SettingDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp),
        color = LightGray_50
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SettingScreen(rememberNavController())
}