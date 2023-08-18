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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.SettingNav
import com.bonobono.presentation.ui.mypage.view.MyPageButton
import com.bonobono.presentation.ui.mypage.view.MyPageInfoCard
import com.bonobono.presentation.ui.mypage.view.MyPageProfileImg
import com.bonobono.presentation.ui.mypage.view.WaveAnimation
import com.bonobono.presentation.ui.theme.WaveBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.MyPageViewModel

@Composable
fun MainMyPageScreen(
    navController: NavController,
    myPageViewModel: MyPageViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit ) {
        myPageViewModel.getProfileImg()
        myPageViewModel.getMemberInfo()
    }
    LazyColumn() {
        item {
            // blue wave background
            WaveBackGround(navController, myPageViewModel)
            // experience and money info box
            MyPageInfoCard(seaAnimalExp = myPageViewModel.memberExp, rewardMoney = 1000)
            // rest buttons
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                MyPageButton(
                    buttonType = "포인트 상점",
                    iconName = "ic_point_store",
                    myPageViewModel = myPageViewModel,
                    navController = navController
                )
                MyPageButton(
                    buttonType = "나의 글",
                    iconName = "ic_feed_history",
                    myPageViewModel = myPageViewModel,
                    navController = navController
                )
                MyPageButton(
                    buttonType = "회원정보 수정",
                    iconName = "ic_profile_edit",
                    myPageViewModel = myPageViewModel,
                    navController = navController
                )
                MyPageButton(
                    buttonType = "로그아웃",
                    iconName = "ic_logout",
                    myPageViewModel = myPageViewModel,
                    navController = navController
                )
            }
        }


    }
}

@Composable
fun WaveBackGround(
    navController: NavController,
    myPageViewModel: MyPageViewModel,
) {  // blue wave background, setting button, profile image, nickname
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
                Spacer(modifier = Modifier.height(32.dp))
                MyPageProfileImg(myPageViewModel.profileImg)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = myPageViewModel.memberNickname,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    MainMyPageScreen(navController = rememberNavController())
}
