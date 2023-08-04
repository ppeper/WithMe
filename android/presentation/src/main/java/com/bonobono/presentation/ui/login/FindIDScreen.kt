package com.bonobono.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.component.BasicButton
import com.bonobono.presentation.ui.component.BasicTextField
import com.bonobono.presentation.ui.component.TextFieldWithButton
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun FindIDScreen(
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
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(value = stringResource(id = R.string.login_name), onValueChange = {})
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithButton(
            value = stringResource(id = R.string.login_phone_number),
            onValueChange = {},
            buttonTxt = R.string.login_require_availability
        ) {

        }
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = stringResource(id = R.string.login_authentic_code),
            onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        BasicButton(text = R.string.login_find_id) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FindIDScreenPreview() {
    FindIDScreen(rememberNavController())
}