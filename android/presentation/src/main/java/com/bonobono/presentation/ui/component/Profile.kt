package com.bonobono.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
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
                .background(Color.White)
                .padding(12.dp)
                .constrainAs(image) {},
            painter = painterResource(id = profileImage),
            contentDescription = "프로필 사진"
        )
        IconButton(
            onClick = { /*TODO*/ },
            Modifier
                .size(24.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 4.dp)
                    end.linkTo(parent.end, margin = 4.dp)
                }
        ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray),
                imageVector = Icons.Filled.Add,
                contentDescription = "프로필 수정"
            )
        }
    }
}

@Composable
fun CharacterProfile(progressBar: @Composable () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.in_sea))
    val progress by animateLottieCompositionAsState(composition)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
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
            ProgressBar(icon = Icons.Filled.AccountBox, title = "제목", percent = 0.3)
            progressBar()
        }
    }
}

@Composable
fun AnimatedCard() {
    val composition  by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.in_sea))
    val progress by animateLottieCompositionAsState(composition)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        // 로티 애니메이션을 Box의 배경으로 추가합니다.
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )

        CharacterProfile {

        }

    }
}


@Preview
@Composable
fun DefaultPreview() {
    AnimatedCard()
}