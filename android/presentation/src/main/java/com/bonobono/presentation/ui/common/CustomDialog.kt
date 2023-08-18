package com.bonobono.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.White

@Composable
fun CheckCountDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    count: Int,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            modifier = modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(
                modifier = modifier
                    .padding(18.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (title.isNotBlank()) {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Black_100,
                            fontWeight = FontWeight(700)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = "사진은 최대 ${count}장까지 업로드 가능합니다.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Black_100,
                        fontWeight = FontWeight(400)
                    )
                )
                SubmitButton(modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp), text = "닫기") {
                    onDismiss()
                }
            }
        }
    }
}

@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    content: String,
    onClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = { }
    ) {
        Card(
            modifier = modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(
                modifier = modifier
                    .padding(18.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = "권한 설정",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Black_100,
                        fontWeight = FontWeight(700)
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = content,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Black_100
                    )
                )
                SubmitButton(modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp), text = "권한 설정하러 가기") {
                    onClicked()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    CheckCountDialog(title = "사진 선택", count = 4) {}
}
@Preview
@Composable
fun PreviewPermissionDialog() {
    PermissionDialog(content = "사진 권한이 필요한 작업입니다.") {}
}