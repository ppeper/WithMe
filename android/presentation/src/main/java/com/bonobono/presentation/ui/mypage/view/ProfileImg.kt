package com.bonobono.presentation.ui.mypage.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.presentation.R

@Composable
fun ProfileEdit(profileImage: String?, clickAction : () -> Unit) {
    Box(
        modifier = Modifier.clickable{clickAction()}
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileImage)
                .error(R.drawable.default_profile)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentDescription = "프로필 이미지"
        )
    }
}

@Composable
fun MyPageProfileImg(profileImage: String?) {
    Surface(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileImage)
                .error(R.drawable.default_profile)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentDescription = "프로필 이미지"
        )
    }
}