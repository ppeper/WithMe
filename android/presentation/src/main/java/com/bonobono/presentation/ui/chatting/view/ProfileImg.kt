package com.bonobono.presentation.ui.chatting.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.presentation.R

@Composable
fun ChatProfileImg(profileImg: String?, profileImgDesc: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(profileImg)
            .error(R.drawable.default_profile)
            .build(),
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape),
        contentDescription = profileImgDesc
    )
}

@Preview(showBackground = true)
@Composable
fun ChatProfileImgPreview() {
//    ChatProfileImg(profileImg = )
}