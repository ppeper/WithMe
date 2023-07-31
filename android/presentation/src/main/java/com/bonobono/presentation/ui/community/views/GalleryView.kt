package com.bonobono.presentation.ui.community.views

import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White

private val TAG = "갤러리"
data class Photo(
    var url: String = "",
    val isSelected: Boolean = false
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryView(
    modifier: Modifier = Modifier,
    onGalleryClick: () -> Unit
) {
    val photoList = loadPhotos()
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {}
    ) { innerPaddings ->
        GalleryGridListView(
            padding = innerPaddings,
            photoList = photoList
        )
    }
}

@Composable
fun GalleryGridListView(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    photoList: List<Photo>
) {
}

@Composable
fun GalleryPhotoView(
    modifier: Modifier = Modifier,
    photo: Photo
) {
    Box(modifier = modifier
        .aspectRatio(1f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.url)
                .build(),
            contentDescription = "갤러리 사진",
            contentScale = ContentScale.Crop
        )
        Box(modifier = modifier.padding(16.dp)
            .align(Alignment.TopEnd)) {
            RoundedCheckView()
        }
    }
}

@Preview
@Composable
fun PreviewGalleryPhotoView() {
    GalleryPhotoView(photo = Photo("aasdasd"))
}

@Composable
fun RoundedCheckView(
    modifier: Modifier = Modifier
) {
    var isCheck by rememberSaveable { mutableStateOf(false) }

    Row {
        Box (
            modifier = modifier
                .clip(CircleShape)
                .border(1.dp, color = White)
        ) {
            Box(
                modifier = modifier
                    .size(25.dp)
                    .background(if (isCheck) PrimaryBlue else White)
                    .clickable { isCheck = !isCheck },
                contentAlignment = Center
            ) {
                if (isCheck) {
                    Icon(painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "선택",
                        tint = White
                    )
                }
            }
        }
    }
}

@Composable
private fun loadPhotos(): List<Photo> {
    val photos = mutableListOf<Photo>()
    val context = LocalContext.current
    val projection = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATA
    )

    val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

    val cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        sortOrder
    )

    cursor?.use { cursor ->
        val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        val nameColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
        val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        while (cursor.moveToNext()) {
            val imageId = cursor.getLong(idColumnIndex)
            val imageName = cursor.getString(nameColumnIndex)
            val imageData = cursor.getString(dataColumnIndex)
            Log.d(TAG, "loadPhotos: $imageName")
            // 이미지 데이터를 사용하여 필요한 작업 수행
            photos.add(Photo().apply {
                url = imageData
            })
        }
    }
    return photos
}