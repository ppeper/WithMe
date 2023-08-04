package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.SettingNav
import com.bonobono.presentation.ui.component.MyPageInfoCard
import com.bonobono.presentation.ui.component.MyPageProfileImg
import com.bonobono.presentation.ui.theme.WaveBlue
import com.bonobono.presentation.ui.theme.White

@Composable
fun MainMyPageScreen(navController: NavController) {
    ConstraintLayout {
        val (waveBackground, infoBox, buttonRow) = createRefs()
        // blue wave background
        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(waveBackground) {
                top.linkTo(parent.top)
                linkTo(start = parent.start, end = parent.end)
            }) {
            WaveBackGround(navController)
        }
        // experience and money info box
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .constrainAs(infoBox) {
                top.linkTo(waveBackground.bottom)
                linkTo(start = parent.start, end = parent.end)
            }) {
        }

        // rest buttons
        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(buttonRow) {
                linkTo(parent.start, parent.end)
                top.linkTo(infoBox.bottom, margin = 16.dp)
            }) {
            MyPageButton(buttonType = "포인트 상점", iconName = "ic_point_store", navController = navController)
            MyPageButton(buttonType = "나의 글", iconName = "ic_feed_history", navController = navController)
            MyPageButton(buttonType = "회원정보 수정", iconName = "ic_profile_edit",  navController = navController)
            MyPageButton(buttonType = "로그아웃", iconName = "ic_logout", navController = navController)
        }
    }
}

@Composable
fun WaveBackGround(navController: NavController) {  // blue wave background, setting button, profile image, nickname
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.blue_wave_background),
            contentDescription = "waveBackground",
            modifier = Modifier.fillMaxWidth()
        )
        WaveAnimation()
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { navController.navigate(SettingNav.route) },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = "settingBtn",
                        tint = WaveBlue
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                    Spacer(modifier = Modifier.height(32.dp))

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "test",
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp)
            }
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

@Composable
fun WaveAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ocean_wave))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = LottieConstants.IterateForever)

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(100.dp)
        )
    }
}
