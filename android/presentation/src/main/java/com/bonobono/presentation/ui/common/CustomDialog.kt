package com.bonobono.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.White

@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onDismissClick: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissClick() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface(
            modifier = modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = White
        ) {
            content()
        }
    }
}

@Composable
fun PhotoCheckDialogContent(
    modifier: Modifier = Modifier,
    count: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(18.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "이미지는 최대 ${count}장 업로드 가능합니다.",
            style = TextStyle(
                fontSize = 16.sp,
                color = Black_100
            )
        )
        SubmitButton(modifier = modifier.fillMaxWidth().height(48.dp), text = "닫기") {
            onClick()
        }
    }
}
