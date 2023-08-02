package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PasswordEditView() {
    Surface {
        Column {
            Text(text = "비밀번호를 바꿔주세요")
            Text(text = "현재 비밀번호를 입력하신 후 새 비밀번호를 입력하세요")
        }
    }
}