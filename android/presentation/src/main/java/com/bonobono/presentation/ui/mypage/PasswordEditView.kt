package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White

@Composable
fun PasswordEditView() {
    var currentPwd by remember { mutableStateOf("") }
    var changePwd by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(color = White, shape = RoundedCornerShape(8.dp))
            ,
        ) {
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "비밀번호를 바꿔주세요")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "현재 비밀번호를 입력하신 후 새 비밀번호를 입력하세요")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = currentPwd,
                    onValueChange = {currentPwd = it},
                    label = { Text(text = "현재 비밀번호")},
                    modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(value = changePwd,
                    onValueChange = {changePwd = it},
                    label = { Text(text = "새 비밀번호")},
                    modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)) {
                    Text(text = "비밀번호 변경하기", modifier = Modifier.padding(vertical = 6.dp))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordEditPreview() {
    PasswordEditView()
}