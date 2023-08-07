package com.bonobono.presentation.ui.mypage.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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