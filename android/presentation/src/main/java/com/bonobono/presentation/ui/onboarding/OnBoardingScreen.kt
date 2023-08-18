package com.bonobono.presentation.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.common.LottieLoader
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.OnBoarding
import com.bonobono.presentation.viewmodel.MainViewModel
import com.bonobono.presentation.viewmodel.MissionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController, mainViewModel: MainViewModel = hiltViewModel()) {
    val onBoardingList = listOf<OnBoarding>(
        OnBoarding.AR,
        OnBoarding.MISSION,
        OnBoarding.CAMPAIGN,
        OnBoarding.COMMUNITY
    )
    Box(modifier = Modifier.fillMaxSize()) {
        val pageCount = 4
        val pageState = rememberPagerState()
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            count = pageCount,
            state = pageState
        ) { page ->
            IconButton(
                modifier =      Modifier.align(Alignment.TopEnd)
                .padding(16.dp)
                .size(24.dp),
                onClick = {
                    mainViewModel.putBoolean(Constants.ONBOADING, true)
                    navController.navigate("main_screen") {
                        navController.currentDestination?.let { it1 -> popUpTo(it1.id){inclusive = true} }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "닫기",
                    modifier = Modifier,
                    tint = LightGray
                )
            }
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LottieLoader(
                    source = onBoardingList[page].animation, modifier = Modifier.size(300.dp)
                )
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = onBoardingList[page].content,
                    style = CustomTextStyle.appNameText.copy(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalPagerIndicator(
                pagerState = pageState,
                activeColor = PrimaryBlue.copy(alpha = 0.9f),
                inactiveColor = PrimaryBlue.copy(alpha = 0.4f)
            )
        }

    }
}


@Preview
@Composable
fun DefaultPreview() {

}