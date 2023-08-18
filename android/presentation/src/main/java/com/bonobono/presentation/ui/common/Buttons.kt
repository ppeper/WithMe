package com.bonobono.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DividerGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White

@Composable
fun SubmitButton(
    modifier: Modifier,
    text: String,
    textStyle: TextStyle = CustomTextStyle.gameGuideTextStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = White,
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text, style = textStyle.copy(color = White))
    }
}

@Composable
fun CancelButton(
    modifier: Modifier,
    text: String,
    textStyle: TextStyle = CustomTextStyle.gameGuideTextStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGray,
            contentColor = Black_100,
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = text, style = textStyle)
    }
}

@Composable
@Preview
fun ButtonsPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        SubmitButton(modifier = Modifier, text = "확인") {}
        CancelButton(modifier = Modifier, text = "취소") {}
    }
}
