package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bonobono.presentation.R

@Composable
fun ProfileEditScreen() {
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