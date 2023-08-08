package com.bonobono.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.BasicTextField
import com.bonobono.presentation.ui.common.TextFieldWithButton
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.wanju_daedunsan
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun JoinScreen(navController: NavController) {
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
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row (verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start) {
                Text(
                    text = stringResource(id = R.string.bonobono_app_name),
                    style = CustomTextStyle.appNameText
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "와 함께",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = wanju_daedunsan,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryBlue
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "아름다운 바다를 만들어요",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = wanju_daedunsan,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryBlue
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            BasicTextField(value = "", hint = "이름", onValueChange = {})
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(value = "", hint = "닉네임", onValueChange = {})
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldWithButton(value = "", onValueChange = {}, hint = "아이디", buttonTxt = R.string.login_check_availability) {

            }
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(value = "", hint = "비밀번호", onValueChange = {})
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(value = "", hint = "비밀번호 확인", onValueChange = {})
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldWithButton(value = "", onValueChange = {}, buttonTxt = R.string.login_resend, hint = "휴대폰 번호") {

            }
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(value = "", hint = "인증번호", onValueChange = {})
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryColorButton(text = R.string.login_join) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinScreenPreview() {
    JoinScreen(rememberNavController())
}