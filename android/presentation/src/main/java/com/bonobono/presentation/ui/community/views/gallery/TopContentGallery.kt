package com.bonobono.presentation.ui.community.views.gallery

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.community.Photo
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.viewmodel.PhotoViewModel

private const val TAG = "싸피"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContentGallery(
    title: String = "",
    navController: NavController,
    currentSelectedPhoto: SnapshotStateList<Photo>,
    photoViewModel: PhotoViewModel = hiltViewModel()
) {
    val pagePathBefore = navController.previousBackStackEntry?.destination?.route
    Log.d(TAG, "TopContentGallery: ${pagePathBefore}")
    CenterAlignedTopAppBar(
        modifier = Modifier.graphicsLayer {
            shadowElevation = 10f
        },
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "뒤로가기"
                )
            }
        },
        actions = {
            TextButton(
                onClick = {
                    if(pagePathBefore == "chatting_edit" || pagePathBefore == "profile_edit") {
                        photoViewModel.setOnePhoto(currentSelectedPhoto[0])
                    } else {
                    // 현재 선택된 사진 추가
                    photoViewModel.selectedPhoto.addAll(currentSelectedPhoto)
                    }
                    navController.popBackStack()
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Black_100,
                    disabledContentColor = TextGray
                ),
                enabled = 0 < currentSelectedPhoto.size
            ) {
                if(pagePathBefore == "chatting_edit" || pagePathBefore == "profile_edit") {
                    if (currentSelectedPhoto.size == 1) {
                        Text(
                            text = "완료",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(400),
                            ),
                        )
                    }
                } else {
                    // 게시판에서 접근
                    if (0 < currentSelectedPhoto.size) {
                        Text(
                            modifier = Modifier.padding(end = 4.dp),
                            text = currentSelectedPhoto.size.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(700),
                                color = PrimaryBlue,
                            )
                        )
                    }
                    Text(
                        text = "완료",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(400),
                        ),
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewCommunityWriteView() {
    TopContentGallery("사진", navController = rememberNavController(), mutableStateListOf())
}