package com.bonobono.presentation.ui.mypage

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.topbar.screen.ProfileEditScreen
import com.bonobono.presentation.ui.community.util.routeMapper
import com.bonobono.presentation.ui.mypage.view.ProfileEdit
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.utils.Converter
import com.bonobono.presentation.utils.PermissionUtils
import com.bonobono.presentation.viewmodel.MyPageViewModel
import com.bonobono.presentation.viewmodel.PhotoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val TAG = "ProfileEditScreen"
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileEditScreen(
    navController: NavController,
    myPageViewModel: MyPageViewModel,
    photoViewModel: PhotoViewModel = hiltViewModel()
) {
    val galleryPermission =
        rememberMultiplePermissionsState(permissions = PermissionUtils.GALLERY_PERMISSIONS)

    val profileImage = if(photoViewModel.selectedOnePhoto.value.url.isBlank()) myPageViewModel.profileImg else photoViewModel.selectedOnePhoto.value.url
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        ProfileEditScreen.buttons
            .onEach { button ->
                when (button) {
                    ProfileEditScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            item {
                ProfileEdit(profileImage = profileImage, clickAction = {
                    galleryPermission.launchMultiplePermissionRequest()
                    Log.d(
                        "TEST",
                        "BoardUpdateScreen: 현재 라우트 ${navController.currentDestination?.route}"
                    )
                    navController.navigate(NavigationRouteName.PROFILE_EDIT_GALLERY)
                })
                Spacer(modifier = Modifier.height(32.dp))
                ProfileEditInfo (
                    infoType = "닉네임",
                    info = myPageViewModel.memberNickname,
                    readOnly = true,
                    singleLine = true
                )
                ProfileEditInfo (
                    infoType = "이름",
                    info = myPageViewModel.memberName,
                    readOnly = true,
                    singleLine = true)
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryColorButton(
                    text = R.string.edit_profile_done,
                    enabled = true,
                    backgroundColor = PrimaryBlue
                ) {
                    val photo = Converter.getRealPathFromUriOrNull(context, Uri.parse(photoViewModel.selectedOnePhoto.value.url))
                    if (photo != null) {
                        myPageViewModel.updateProfileImg(photo)
                    }
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun ProfileEditInfo(
    infoType: String,
    info: String,
    readOnly: Boolean,
    singleLine: Boolean
) {
    Log.d(TAG, "ProfileEditInfo: $info")
    var txt by remember {
        mutableStateOf(info)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = infoType,
            style = TextStyle(
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = txt,
            onValueChange = { txt = it },
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly,
            singleLine = singleLine,
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = if (infoType == "닉네임") Black_100 else Black_70
            ),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Black_70,
                unfocusedIndicatorColor = Black_70,
                disabledIndicatorColor = Black_70,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}