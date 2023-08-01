package com.bonobono.presentation.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.White

@Composable
fun ProfilePhoto(profileImage: Int) {
    Image(
        modifier = Modifier
            .clip(CircleShape)
            .padding(8.dp)
            .background(White),
        painter = painterResource(id = profileImage),
        contentDescription = "프로필 사진"
    )
}

@Composable
fun AnimatedProfile(profileImage: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            LottieLoader(source = R.raw.animation_card, modifier = Modifier.zIndex(-0.1f).fillMaxWidth().height(this.maxWidth))
        }
        ProfilePhoto(profileImage = profileImage)
    }
}