package com.bonobono.presentation.ui.login

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.BasicTextField
import com.bonobono.presentation.ui.common.PasswordTextField
import com.bonobono.presentation.ui.common.TextFieldWithButton
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.wanju_daedunsan
import com.bonobono.presentation.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun JoinScreen(navController: NavController) {
    val viewModel: SignUpViewModel = viewModel()
    val buttonEnabled = viewModel.checkAllAllowed
    val buttonColor = if (buttonEnabled) PrimaryBlue else viewModel.buttonColor

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
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.bonobono_app_name),
                    style = CustomTextStyle.appNameText
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "와 함께",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = wanju_daedunsan,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryBlue
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "아름다운 바다를 만들어요",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = wanju_daedunsan,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryBlue
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            BasicTextField(
                value = viewModel.name,
                hint = "이름",
                keyboardType = KeyboardType.Text
            ) { name -> viewModel.updateName(name) }
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = viewModel.nickName,
                hint = "닉네임",
                keyboardType = KeyboardType.Text
            ) { nickName -> viewModel.updateNickName(nickName) }
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithButton(
                value = viewModel.username,
                onValueChange = { userName -> viewModel.updateUserName(userName) },
                hint = "아이디",
                keyboardType = KeyboardType.Text,
                buttonTxt = R.string.login_check_availability
            ) {
                viewModel.checkUserName()
            }
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = viewModel.password,
                hint = "비밀번호",
                keyboardType = KeyboardType.Password
            ) { password -> viewModel.updatePassword(password) }
            Spacer(modifier = Modifier.height(8.dp))
            PasswordTextField(
                value = viewModel.passwordCheck,
                hint = "비밀번호 확인",
                supportingText = "",
                keyboardType = KeyboardType.Password
            ) { passwordChk -> viewModel.updatePasswordCheck(passwordChk) }
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithButton(
                value = viewModel.phoneNum,
                onValueChange = { phoneNum -> viewModel.updatePhoneNum(phoneNum) },
                buttonTxt = R.string.login_phonenum_check_send,
                keyboardType = KeyboardType.Phone,
                hint = "휴대폰 번호"
            ) {

            }
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = viewModel.validationCode,
                hint = "인증번호",
                onValueChange = { validationCode -> viewModel.updateValidationCode(validationCode) },
                keyboardType = KeyboardType.Number,
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryColorButton(text = R.string.login_join, enabled = buttonEnabled, backgroundColor = buttonColor) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinScreenPreview() {
    JoinScreen(rememberNavController())
}