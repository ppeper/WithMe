package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.topbar.screen.ProfileEditScreen
import com.bonobono.presentation.ui.mypage.view.ProfileEdit
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Black_70
import com.bonobono.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileEditScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        ProfileEditScreen.buttons
            .onEach { button ->
                when (button) {
                    ProfileEditScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            item {
                ProfileEdit(profileImage = R.drawable.beluga_whale)
                Spacer(modifier = Modifier.height(32.dp))
                ProfileEditInfo(
                    infoType = "닉네임",
                    info = "test",
                    readOnly = false,
                    singleLine = true
                )
                ProfileEditInfo(infoType = "이름", info = "test1", readOnly = true, singleLine = true)
                ProfileEditInfo(
                    infoType = "휴대폰 번호",
                    info = "1234-1234",
                    readOnly = true,
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryColorButton(
                    text = R.string.edit_profile_done,
                    enabled = true,
                    backgroundColor = PrimaryBlue
                ) {

                }
            }
        }
    }
}

@Composable
fun ProfileEditInfo(
    infoType: String,
    info: String,
    readOnly: Boolean,
    singleLine: Boolean
) {
    var txt by remember {
        mutableStateOf(info)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = infoType,
            style = TextStyle(
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = txt,
            onValueChange = { txt = it },
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly,
            singleLine = singleLine,
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = if (infoType == "닉네임") Black_100 else Black_70
            ),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Black_70,
                unfocusedIndicatorColor = Black_70,
                disabledIndicatorColor = Black_70,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}