package com.bonobono.presentation.ui.login.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bonobono.presentation.ui.common.text.CustomTextStyle.loginDarkGrayText

private const val TAG = "싸피"
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

@Composable
fun LoginTextButton(
    text: String,
    route: String,
    navController: NavController
) {

    TextButton(onClick = {
        navController.navigate(route = route)
    }) {
        Text(text = text,
            style = loginDarkGrayText)
    }
}