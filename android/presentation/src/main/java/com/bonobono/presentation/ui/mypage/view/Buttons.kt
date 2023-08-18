package com.bonobono.presentation.ui.mypage.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.LoginNav
import com.bonobono.presentation.ui.MainNav
import com.bonobono.presentation.ui.PointStoreNav
import com.bonobono.presentation.ui.ProfileEditNav
import com.bonobono.presentation.ui.mypage.PasswordEditView
import com.bonobono.presentation.ui.mypage.WithdrawView
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.LoginViewModel
import com.bonobono.presentation.viewmodel.MyPageViewModel

@Composable
fun SettingOptions(
    option: String,
    color: Color
) {
    val context = LocalContext.current
    var isPasswordDialogVisible by remember { mutableStateOf(false) }
    var isWithdrawDialogVisible by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    when (option) {
                        "권한설정" -> {
                            navigateToAppSettings(context)
                        }

                        "비밀번호" -> {
                            isPasswordDialogVisible = true
                        }

                        "회원탈퇴" -> {
                            isWithdrawDialogVisible = true
                        }

                        else -> {

                        }
                    }
                })
        ) {
            Text(
                text = option,
                style = TextStyle(
                    color = color,
                    fontSize = 14.sp
                )
            )
            Icon(
                modifier = Modifier
                    .size(24.dp, 24.dp),
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = "rightBtn"
            )
            if (isPasswordDialogVisible) {
                PasswordEditView(onDismiss = { isPasswordDialogVisible = false })
            } else if (isWithdrawDialogVisible) {
                WithdrawView(onDismissRequest = { isWithdrawDialogVisible = false })
            }
        }
    }
}

fun navigateToAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}


@Composable
fun MyPageButton(buttonType: String, iconName: String, navController: NavController, myPageViewModel: MyPageViewModel) {
    val loginViewModel : LoginViewModel = hiltViewModel()
    val newNavController = rememberNavController()
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                when (buttonType) {
                    "포인트 상점" -> {
                        navController.navigate(PointStoreNav.route)
                    }

                    "나의 글" -> {
                    }

                    "회원정보 수정" -> {
                        navController.navigate(ProfileEditNav.route)
                    }

                    "로그아웃" -> {
                        loginViewModel.logout()
                        navController.popBackStack(LoginNav.route, false)
                        navController.navigate("backToLogin")
                    }

                    else -> {

                    }
                }
            }) {
        val context = LocalContext.current
        val drawableId = remember(iconName) {
            context.resources.getIdentifier(iconName, "drawable", context.packageName)
        }
        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = drawableId),
                contentDescription = "buttonIcon",
//            tint = WaveBlue,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
                    .size(40.dp)
                    .padding(8.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(buttonType,
                fontSize = 16.sp)
        }
        Icon(painter = painterResource(id = R.drawable.ic_right),
            contentDescription = "rightIcon",
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun settingButtonPreview() {
    SettingOptions(option = "test", color = DarkGray)
}