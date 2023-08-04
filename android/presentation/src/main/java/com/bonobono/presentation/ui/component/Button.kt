package com.bonobono.presentation.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.theme.PrimaryBlue

@Composable
fun BasicButton(
    @StringRes text: Int,
    action: () -> Unit
) {
    Button(modifier = Modifier
        .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PrimaryBlue
        ),
        onClick = { action }) {
        BasicButtonText(text = stringResource(text))
    }
}

@Composable
fun SNSButton(
    @DrawableRes img : Int,
    contentDescription : String,
    action: () -> Unit
) {
    IconButton(onClick = { action }) {
        Image(painter = painterResource(img),
            contentDescription = contentDescription,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp))
    }
}