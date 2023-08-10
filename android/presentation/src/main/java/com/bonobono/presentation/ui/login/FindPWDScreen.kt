package com.bonobono.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.BasicTextField
import com.bonobono.presentation.ui.common.TextFieldWithButton
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun FindPWDScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        SettingScreen.buttons
            .onEach { button ->
                when (button) {
                    SettingScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.bonobono_app_name),
            style = CustomTextStyle.appNameText
        )
        Spacer(modifier = Modifier.height(32.dp))
        BasicTextField(
            value = stringResource(id = R.string.login_name),
            hint = "이름",
            keyboardType = KeyboardType.Text
        ) {}
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = stringResource(id = R.string.login_id),
            hint = "아이디",
            keyboardType = KeyboardType.Text
        ) {}
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithButton(
            value = stringResource(id = R.string.login_phone_number),
            onValueChange = {},
            buttonTxt = R.string.login_require_availability,
            enable = true,
            keyboardType = KeyboardType.Phone,
            hint = "휴대폰 번호"
        ) {

        }
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = stringResource(id = R.string.login_authentic_code),
            onValueChange = {},
            hint = "인증번호",
            keyboardType = KeyboardType.Number,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryColorButton(text = R.string.login_find_password, enabled = true, backgroundColor = PrimaryBlue) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FindPWDScreenPreview() {
    FindPWDScreen(rememberNavController())
}