package com.bonobono.presentation.ui.community.views.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.bonobono.presentation.ui.community.Photo
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.PhotoViewModel

data class PhotoSelected(
    val url: String,
    val isVisible: Boolean = true
)

@Composable
fun PhotoSelectedListView(
    photoViewModel: PhotoViewModel
) {
    val photos = photoViewModel.selectedPhoto
    LazyRow{
        items(photos) { photo ->
            PhotoSelectedView(photo = photo, onItemDeleted = {
                photoViewModel.removePhoto(photo)
            })
        }
    }
}

@Composable
fun PhotoSelectedView(
    modifier: Modifier = Modifier,
    photo: Photo,
    onItemDeleted: (Photo) -> Unit
) {
    Box(
        modifier = modifier.size(70.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.BottomStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.url)
                .build(),
            contentDescription = "선택된 사진",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .size(18.dp)
                .border(
                    width = 1.dp,
                    color = White,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(color = Black_100)
                .align(Alignment.TopEnd)
        ) {
            IconButton(
                onClick = { onItemDeleted(photo) },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "삭제",
                    tint = White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPhotoSelectedView() {
    PhotoSelectedView(photo = Photo(""), onItemDeleted = {})
}