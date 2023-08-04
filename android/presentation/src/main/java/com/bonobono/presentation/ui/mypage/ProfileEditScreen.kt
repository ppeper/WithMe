package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.topbar.screen.ProfileEditScreen
import com.bonobono.presentation.ui.component.ProfileEdit
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileEditScreen(
    navController: NavController
) {
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
    Surface {
        Column {
            //ProfileEdit(profileImage = R.drawable.beluga_whale)
            ProfileEditInfo(infoType = "닉네임", info = "test")
            ProfileEditInfo(infoType = "이름", info = "test1")
            ProfileEditInfo(infoType = "전화번호", info = "1234-1234")
        }
    }
}

@Composable
fun ProfileEditInfo(infoType:String, info:String) {
    var txt by remember {
        mutableStateOf(info)
    }
    Column {
        Text(text = infoType)
        TextField(value = txt,
            label = { Text(info)},
            onValueChange = {txt = it})
    }
}