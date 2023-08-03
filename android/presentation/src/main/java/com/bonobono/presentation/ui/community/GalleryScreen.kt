package com.bonobono.presentation.ui.community

import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CustomDialog
import com.bonobono.presentation.ui.common.PhotoCheckDialogContent
import com.bonobono.presentation.ui.community.views.gallery.TopContentGallery
import com.bonobono.presentation.ui.theme.Black_20
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.PhotoViewModel

private val TAG = "갤러리"

data class Photo(
    var url: String = "",
    var isSelected: Boolean = false,
    val isVisible: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    photoViewModel: PhotoViewModel
) {
    val photoList = loadPhotos()
    val currentSelectedPhoto = remember {
        mutableStateListOf<Photo>()
    }
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            TopContentGallery(
                title = "사진", navController,
                photoViewModel = photoViewModel,
                currentSelectedPhoto = currentSelectedPhoto
            )
        }
    ) {
        Surface(
            modifier = modifier.padding(it)
        ) {
            GalleryGridListView(
                photoList = photoList,
                photoViewModel = photoViewModel,
                currentSelectedPhoto = currentSelectedPhoto)
        }
    }
}

@Composable
@Preview
fun PreviewGalleryScreen() {
    GalleryScreen(navController = rememberNavController(), photoViewModel = PhotoViewModel())
}

@Composable
fun GalleryGridListView(
    photoList: List<Photo>,
    photoViewModel: PhotoViewModel,
    currentSelectedPhoto: SnapshotStateList<Photo>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(photoList) { photo ->
            GalleryPhotoView(
                photo = photo,
                currentSelectedPhoto = currentSelectedPhoto,
                photoViewModel = photoViewModel,
                onPhotoSelected = { photo ->
                    if (photo.isSelected) {
                        currentSelectedPhoto.add(photo)
                    } else {
                        currentSelectedPhoto.remove(photo)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewGalleryGridListView() {
    GalleryGridListView(
        photoList = mutableListOf<Photo>()
            .apply {
                repeat(20) {
                    add(Photo(url = "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"))
                }
            },
        photoViewModel = PhotoViewModel(),
        currentSelectedPhoto = remember {
            mutableStateListOf()
        }
    )
}

@Composable
fun GalleryPhotoView(
    modifier: Modifier = Modifier,
    photo: Photo,
    photoViewModel: PhotoViewModel,
    currentSelectedPhoto: SnapshotStateList<Photo>,
    onPhotoSelected: (Photo) -> Unit,
) {
    var isCheck by rememberSaveable { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val previousCount = photoViewModel.selectedPhoto.size

    if (showDialog) {
        CustomDialog(content = {
            PhotoCheckDialogContent(count = (10 - previousCount).toString()) {
                showDialog = !showDialog
            }
        }) {
            showDialog = !showDialog
        }
    }
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        AsyncImage(
            modifier = modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.url)
                .build(),
            contentDescription = "갤러리 사진",
            contentScale = ContentScale.Crop
        )
        if (isCheck) {
            Box(
                modifier = modifier
                    .aspectRatio(1f)
                    .background(color = Black_20)
                    .border(2.dp, color = PrimaryBlue)
            )
        }
        Box(
            modifier = modifier
                .padding(8.dp)
                .align(Alignment.TopEnd)
        ) {
            Box(
                modifier = modifier
                    .size(24.dp)
                    .border(
                        1.dp,
                        color = (if (isCheck) PrimaryBlue else White),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(if (isCheck) PrimaryBlue else Color.Transparent)
                    .clickable {
                        // 이미지는 최대 10장 업로드 가능하도록 설정
                        if (10 <= previousCount + currentSelectedPhoto.size) {
                            showDialog = true
                        } else {
                            isCheck = !isCheck
                            photo.isSelected = isCheck
                            onPhotoSelected(photo)
                        }
                    },
                contentAlignment = Center
            ) {
                if (isCheck) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
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
        val nameColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
        val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        while (cursor.moveToNext()) {
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