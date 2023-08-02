package com.bonobono.presentation.ui.community.views

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.White

data class PhotoSelected(
    val url: String,
    val isVisible: Boolean = true
)

@Composable
fun PhotoSelectedListView(
    selectedPhotos: List<PhotoSelected> = emptyList()
) {
    val photos = remember { selectedPhotos.toMutableStateList() }
    LazyRow {
        items(photos, key = { it.url }) { photo ->
            PhotoSelectedView(photo = photo, onItemDeleted = {
                photos.remove(it)
            })
        }
    }
}

@Composable
fun PhotoSelectedView(
    modifier: Modifier = Modifier,
    photo: PhotoSelected,
    onItemDeleted: (PhotoSelected) -> Unit
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
    PhotoSelectedView(photo = PhotoSelected(""), onItemDeleted = {})
}

@Preview
@Composable
fun PreviewMultiplePhotoSelectedView() {
    val list = listOf(
        PhotoSelected("https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"),
        PhotoSelected("https://images.unsplash.com/photo-1682685796063-d2604827f7b3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"),
        PhotoSelected("https://images.unsplash.com/photo-1690535707954-597ff9dbcdc3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=894&q=80"),
        PhotoSelected("https://plus.unsplash.com/premium_photo-1690297971595-1f1676616375?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=435&q=80"),
        PhotoSelected("https://images.unsplash.com/photo-1690666094915-6a104f803693?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=366&q=80"),
        PhotoSelected("https://images.unsplash.com/photo-1682686580036-b5e25932ce9a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1075&q=80")
    )
    PhotoSelectedListView(selectedPhotos = list)
}