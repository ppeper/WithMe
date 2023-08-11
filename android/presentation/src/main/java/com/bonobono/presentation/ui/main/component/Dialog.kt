package com.bonobono.presentation.ui.main.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.White

@Composable
fun OverDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    content: String,
    commentary: String = "",
    source: Int,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface(
            modifier = modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = White
        ) {
            Column(
                modifier = modifier
                    .padding(18.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GifLoader(modifier = Modifier.size(100.dp), source = source)
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = title,
                    style = CustomTextStyle.quizTitleStyle,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = content,
                    style = CustomTextStyle.quizContentStyle
                )
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = commentary,
                    style = CustomTextStyle.quizContentStyle
                )
                SubmitButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(48.dp), text = "나가기",
                    textStyle = CustomTextStyle.quizContentStyle
                ) {
                    onDismiss()
                }
            }
        }
    }
}