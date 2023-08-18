package com.bonobono.presentation.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.domain.model.registration.Member
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.LoginNav
import com.bonobono.presentation.ui.common.BasicTextField
import com.bonobono.presentation.ui.common.SupportTxtTextField
import com.bonobono.presentation.ui.common.TextFieldWithButton
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.wanju_daedunsan
import com.bonobono.presentation.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val TAG = "싸피"

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JoinScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val buttonEnabled = viewModel.checkAllAllowed
    val buttonColor = if (buttonEnabled) PrimaryBlue else viewModel.buttonColor
    val userNameBtnEnabled = viewModel.checkUserNameState
    val userNickNameBtnEnabled = viewModel.checkNickNameState
    val passwordEnabled = viewModel.checkPwdValid
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
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
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        LazyColumn(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp).fillMaxHeight()
        ) {
            item {
                ConstraintLayout (modifier = Modifier.fillParentMaxHeight()){
                    val (titleCol, infoCol, signUpBtn) = createRefs()
                    createVerticalChain(titleCol,infoCol,signUpBtn, chainStyle = ChainStyle.SpreadInside)
                    Column (modifier = Modifier.constrainAs(titleCol){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }) {
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
                    }
                    Column(
                        modifier = Modifier.constrainAs(infoCol) {
                            linkTo(start = parent.start, end = parent.end)
                            linkTo(top = titleCol.bottom, bottom = signUpBtn.top)
                        },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BasicTextField(
                            value = viewModel.name,
                            hint = "이름",
                            keyboardType = KeyboardType.Text
                        ) { name -> viewModel.updateName(name) }
                        Spacer(modifier = Modifier.height(8.dp))
                        TextFieldWithButton(
                            value = viewModel.nickName,
                            hint = "닉네임",
                            onValueChange = { nickName -> viewModel.updateNickName(nickName) },
                            keyboardType = KeyboardType.Text,
                            enable = !userNickNameBtnEnabled,
                            buttonTxt = R.string.login_check_availability
                        ) {
                            coroutineScope.launch {
                                val result = viewModel.checkNickName()
                                if (result == "success") {
                                    snackbarHostState.showSnackbar("사용 가능한 닉네임입니다.")
                                } else {
                                    snackbarHostState.showSnackbar("이미 존재하는 닉네임입니다.")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        TextFieldWithButton(
                            value = viewModel.username,
                            onValueChange = { userName -> viewModel.updateUserName(userName) },
                            hint = "아이디",
                            keyboardType = KeyboardType.Text,
                            enable = !userNameBtnEnabled,
                            buttonTxt = R.string.login_check_availability
                        ) {
                            coroutineScope.launch {
                                val result = viewModel.checkUserName()
                                if (result == "success") {
                                    snackbarHostState.showSnackbar("사용 가능한 아이디입니다.")
                                } else {
                                    snackbarHostState.showSnackbar("이미 존재하는 아이디입니다.")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        BasicTextField(
                            value = viewModel.password,
                            hint = "비밀번호",
                            keyboardType = KeyboardType.Password
                        ) { password -> viewModel.updatePassword(password) }
                        Spacer(modifier = Modifier.height(8.dp))
                        SupportTxtTextField(
                            value = viewModel.passwordCheck,
                            hint = "비밀번호 확인",
                            supportingText = viewModel.passwordSupportTxt,
                            enable = passwordEnabled,
                            keyboardType = KeyboardType.Password
                        ) { passwordChk -> viewModel.updatePasswordCheck(passwordChk) }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                    Column (modifier = Modifier.constrainAs(signUpBtn){
                        linkTo(start = parent.start, end = parent.end)
                        bottom.linkTo(parent.bottom, 8.dp)
                    }){
                        PrimaryColorButton(

                            text = R.string.login_join,
                            enabled = buttonEnabled,
                            backgroundColor = buttonColor,
                            action = {
                                Log.d(TAG, "JoinScreen: clicked!!")
                                viewModel.signUp()
                                navController.navigate(LoginNav.route)
                            })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinScreenPreview() {
    JoinScreen(rememberNavController())
}