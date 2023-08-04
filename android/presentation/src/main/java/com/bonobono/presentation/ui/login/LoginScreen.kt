package com.bonobono.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.BasicTextField
import com.bonobono.presentation.ui.common.button.BasicButton
import com.bonobono.presentation.ui.common.button.SNSButton
import com.bonobono.presentation.ui.common.text.LoginDarkGrayText
import com.bonobono.presentation.ui.common.topbar.screen.FindIDScreen
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue

@Composable
fun LoginScreen() {
    val navController = rememberNavController()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center) {
        BasicTextField(
            value = stringResource(id = R.string.login_id),
            onValueChange = {})
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = stringResource(id = R.string.login_password),
            onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        AutoLogin()
        Spacer(modifier = Modifier.height(32.dp))
        BasicButton(text = R.string.login_login) {
            
        }
        Spacer(modifier = Modifier.height(16.dp))
        LoginHelpOptions(navController)
        Spacer(modifier = Modifier.height(60.dp))
        SNSLoginGuide()
        Spacer(modifier = Modifier.height(12.dp))
        SNSLoginButtons()
    }
}


@Composable
fun AutoLogin() {
    var autoLoginState by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = autoLoginState, colors = CheckboxDefaults.colors(
                checkedColor = PrimaryBlue,
                uncheckedColor = LightGray
            ), modifier = Modifier.size(24.dp), onCheckedChange = { isChecked ->
                autoLoginState = isChecked
            })
        Spacer(modifier = Modifier.width(8.dp))
        LoginDarkGrayText(text = "로그인 상태 유지")
    }
}


@Composable
fun LoginHelpOptions(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LoginTextButton(text = "아이디 찾기") {
            navController.navigate(FindIDScreen.route)
        }
        LoginHelpOptionDivider()
        LoginTextButton(text = "비밀번호 찾기") {
            
        }
        LoginHelpOptionDivider()
        LoginTextButton(text = "회원가입") {
            
        }
    }
}

@Composable
fun LoginTextButton(
    text : String,
    action : () -> Unit
) {
    TextButton(onClick = { action }) {
        LoginDarkGrayText(text)
    }
}

@Composable
fun LoginHelpOptionDivider() {
    Spacer(modifier = Modifier.width(8.dp))
    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .height(20.dp)
            .width(1.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun SNSLoginGuide() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        LoginDarkGrayText(text = "SNS 계정으로 로그인")
        Spacer(modifier = Modifier.width(8.dp))
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun SNSLoginButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SNSButton(img = R.drawable.ic_google, contentDescription = "google") {

        }
        Spacer(modifier = Modifier.width(20.dp))
        SNSButton(img = R.drawable.ic_kakao, contentDescription = "kakao") {

        }
        Spacer(modifier = Modifier.width(20.dp))
        SNSButton(img = R.drawable.ic_naver, contentDescription = "naver") {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}