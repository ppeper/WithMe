package com.bonobono.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.LightGray

@Composable
fun LoginScreen() {
    var idText by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var pwdText by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var buttonClicked by remember {
        mutableStateOf(false)
    }
    Column(verticalArrangement = Arrangement.Center) {
        OutlinedTextField(value = idText,
            label = { Text(text = "아이디") },
            colors = TextFieldDefaults.colors(LightGray),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onValueChange = {
                idText = it
            })
        OutlinedTextField(value = pwdText,
            label = { Text(text = "비밀번호") },
            colors = TextFieldDefaults.colors(LightGray),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onValueChange = {
                pwdText = it
            })
        Spacer(modifier = Modifier.height(16.dp))
        AutoLogin()
        Spacer(modifier = Modifier.height(32.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = { buttonClicked = !buttonClicked }) {
            Text(text = "로그인", fontWeight = FontWeight.Bold, modifier = Modifier.padding(6.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        LoginHelpOptions()
        Spacer(modifier = Modifier.height(60.dp))
        SNSLoginGuide()
        Spacer(modifier = Modifier.height(4.dp))
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
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = autoLoginState, colors = CheckboxDefaults.colors(
                checkedColor = Color.Blue,
                uncheckedColor = Color.LightGray
            ), modifier = Modifier.size(24.dp), onCheckedChange = { isChecked ->
                autoLoginState = isChecked
            })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "로그인 상태 유지", color = Color.Gray)
    }
}


@Composable
fun LoginHelpOptions() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "아이디 찾기", color = Color.Gray)
        }
        LoginHelpOptionDivider()
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "비밀번호 찾기", color = Color.Gray)
        }
        LoginHelpOptionDivider()
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "회원가입", color = Color.Gray)
        }
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
        Text(text = "SNS 계정으로 로그인", color = Color.Gray)
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
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "google",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.ic_kakao),
                contentDescription = "kakao",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.ic_naver),
                contentDescription = "naver",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}