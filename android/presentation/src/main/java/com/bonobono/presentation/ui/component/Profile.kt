package com.bonobono.presentation.ui.component

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bonobono.presentation.R

@Composable
fun ProfileEdit(profileImage: Int) {
    ConstraintLayout(
    ) {
        val (image, button) = createRefs()
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .padding(12.dp)
                .constrainAs(image) {},
            painter = painterResource(id = profileImage),
            contentDescription = "프로필 사진"
        )
    }
}

@Composable
fun CharacterProfile(progressBar: @Composable () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.in_sea))
    val progress by animateLottieCompositionAsState(composition)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ProfileEdit(profileImage = R.drawable.beluga_whale)
            }
        }
    }
}

@Composable
fun AnimatedCard() {
    val composition  by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_card))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // 로티 애니메이션을 Box의 배경으로 추가합니다.
        LottieAnimation(
            modifier = Modifier
                .zIndex(-0.1f)
                .fillMaxWidth()
                .height(300.dp),
            composition = composition,
            progress = { progress },
        )
        CharacterProfile {

        }
    }
}

@Composable
fun MyPageProfileImg() {
    val imagePainter = painterResource(id = R.drawable.sea_turtle)
    Surface(
        modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .background(Color.LightGray))
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


@Preview
@Composable
fun DefaultPreview() {
    AnimatedCard()
}