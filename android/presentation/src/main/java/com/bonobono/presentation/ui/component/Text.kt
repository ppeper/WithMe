package com.bonobono.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White

@Composable
fun HeaderTwoText(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Black_100
    )
}

@Composable
fun BasicButtonText(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = White,
        modifier = Modifier.padding(vertical = 6.dp)
    )
}

@Composable
fun TextFieldText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = TextGray
    )
}

@Composable
fun LoginDarkGrayText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = DarkGray)
}