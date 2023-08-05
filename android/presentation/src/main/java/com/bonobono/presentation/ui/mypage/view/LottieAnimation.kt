package com.bonobono.presentation.ui.mypage.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bonobono.presentation.R

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
