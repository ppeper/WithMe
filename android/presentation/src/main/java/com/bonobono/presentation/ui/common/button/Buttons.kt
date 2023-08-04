package com.bonobono.presentation.ui.common.button

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.common.text.BasicButtonText
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White

@Composable
fun PrimaryButton(content: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = White
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.buttonElevation(4.dp),
    ) {
        Text(text = content)
    }
}

@Composable
fun PrimaryColorButton(
    @StringRes text: Int,
    action: () -> Unit
) {
    Button(modifier = Modifier
        .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue
        ),
        onClick = { action }) {
        BasicButtonText(text = stringResource(text))
    }
}
