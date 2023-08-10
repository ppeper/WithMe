package com.bonobono.presentation.ui.common.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.common.text.CustomTextStyle.primaryColorBtnText
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White

@Composable
fun PrimaryButton(
    content: String,
    modifier: Modifier,
    isEnabled: MutableState<Boolean> = mutableStateOf(true),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = White
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.buttonElevation(4.dp),
        enabled = isEnabled.value
    ) {
        Text(text = content)
    }
}

@Composable
fun PrimaryColorButton(
    @StringRes text: Int,
    enabled :Boolean,
    backgroundColor : Color,
    action: () -> Unit
) {
    Button(modifier = Modifier
        .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(6.dp),
        enabled = enabled,
        onClick = action) {
        Text(text = stringResource(text),
            style = primaryColorBtnText,
            modifier = Modifier.padding(vertical = 6.dp)
        )
    }
}
