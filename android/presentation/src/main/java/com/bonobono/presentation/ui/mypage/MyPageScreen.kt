package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.constraintlayout.compose.ConstraintLayout
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.component.MyPageInfoCard

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
            WaveBackGround()
        }
        // experience and money info box
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .constrainAs(infoBox) {
                top.linkTo(waveBackground.bottom)
                linkTo(start = parent.start, end = parent.end)
            }) {
            MyPageInfoCard("1,000", "1,000")
        }

        // rest buttons
        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(buttonRow) {
                linkTo(parent.start, parent.end)
                top.linkTo(infoBox.bottom, margin = 16.dp)
            }) {
            MyPageButton(imgUrl = "", buttonType = "포인트 상점")
            MyPageButton(imgUrl = "", buttonType = "나의 글")
            MyPageButton(imgUrl = "", buttonType = "회원정보 수정")
            MyPageButton(imgUrl = "", buttonType = "로그아웃")
        }

    }
}

@Composable
fun WaveBackGround() {  // blue wave background, setting button, profile image, nickname
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.blue_wave_background),
            contentDescription = "waveBackground",
            modifier = Modifier.fillMaxWidth()
        )
//        WaveAnimation()
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.setting_icon),
                        contentDescription = "settingBtn",
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                    Spacer(modifier = Modifier.height(32.dp))
                MyPageProfileImg()
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "test")
            }
        }
    }
}

@Composable
fun MyPageProfileImg() {
    val imagePainter = painterResource(id = R.drawable.sea_turtle)
    Box(
        modifier = Modifier
            .size(128.dp)
            .clip(CircleShape)
            .shadow(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .background(Color.LightGray)
                .clip(CircleShape)
        )
        Image(
            painter = imagePainter,
            contentDescription = "profileImg",
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
@Composable
fun MyPageButton(imgUrl: String, buttonType: String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (buttonIcon, buttonTypeStr, rightButton) = createRefs()
//        if(imageBitmap != null) {
//            Icon(painter = BitmapPainter(imageBitmap), contentDescription = "buttonIcon")
//        }
        Icon(painter = painterResource(id = R.drawable.help_icon),
            contentDescription = "buttonIcon",
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
        Icon(painter = painterResource(id = R.drawable.right_icon),
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

//@Composable
//fun WaveAnimation() {
//    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ocean_wave2))
//    val progress by animateLottieCompositionAsState(composition = composition,
//        iterations = LottieConstants.IterateForever)
//
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .height(300.dp),
//    ) {
//        LottieAnimation(
//            composition = composition,
//            progress = { progress },
//            contentScale = ContentScale.FillWidth,
//            modifier = Modifier
//                .height(100.dp)
//        )
//    }
//}
