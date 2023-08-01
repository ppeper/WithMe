package com.bonobono.presentation.ui.community.views

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.community.Photo
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContentGallery(
    title: String = "",
    navController: NavController,
    selectedPhotos: SnapshotStateList<Photo>,
) {
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
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Black_100,
                    disabledContentColor = TextGray
                )
            ) {
                if (0 < selectedPhotos.size) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = selectedPhotos.size.toString(),
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
    )
}

@Preview
@Composable
fun PreviewCommunityWriteView() {
    TopContentGallery("사진", navController = rememberNavController(), remember { mutableStateListOf() })
}