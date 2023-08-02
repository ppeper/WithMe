package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.component.ProfileEdit

@Composable
fun ProfileEditScreen() {
    Surface {
        Column {
            ProfileEdit(profileImage = R.drawable.beluga_whale)
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