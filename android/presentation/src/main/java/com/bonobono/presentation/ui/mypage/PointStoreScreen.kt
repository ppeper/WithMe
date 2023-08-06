package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.topbar.screen.PointStoreScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun PointStoreScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        PointStoreScreen.buttons
            .onEach { button ->
                when (button) {
                    PointStoreScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    Column (modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        PointStoreAnimation()
        Text(text = "포인트 상점은 아직 오픈되지 않았습니다!")
    }
}

@Composable
fun PointStoreAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.point_store_coming_soon))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = LottieConstants.IterateForever)
    Box(modifier = Modifier.fillMaxWidth()) {
        LottieAnimation(composition = composition,
            progress = {progress},
            contentScale = ContentScale.FillHeight)
    }
}

@Preview(showBackground = true)
@Composable
fun PointStorePreview() {
    PointStoreScreen(rememberNavController())
}