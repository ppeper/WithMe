package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


private const val TAG = "SignUpViewModel"

class SignUpViewModel : ViewModel() {
    var checkPwdValid = false
    var checkAllAllowed by mutableStateOf(false)
        private set
    var buttonColor by mutableStateOf(LightGray)
        private set

    private var checkUserNameValid = false
    private var phoneNumValid = false
    var username by mutableStateOf("")
        private set

    fun updateUserName(input: String) {
        username = input
    }
    fun checkUserName() {

    }

    var name by mutableStateOf("")
        private set

    fun updateName(input: String) {
        name = input
        updateButtonState()
    }


    var nickName by mutableStateOf("")
        private set

    fun updateNickName(input: String) {
        nickName = input
        updateButtonState()
    }

    var password by mutableStateOf("")
    fun updatePassword(input: String) {
        password = input
    }

    var passwordCheck by mutableStateOf("")
    fun updatePasswordCheck(input: String) {
        passwordCheck = input
        if(password != "" && passwordCheck != "") {
            // 둘 다 값이 있는 경우 비밀번호 일치 확인
            checkPwdValid = password == passwordCheck
        } else checkPwdValid = false
    }

    var phoneNum by mutableStateOf("")
    fun updatePhoneNum(input: String) {
        phoneNum = input
    }

    var validationCode by mutableStateOf("")
    fun updateValidationCode(input: String) {
        // 인증 번호는 서버에서 받은 인증 코드랑 같은지 비교
        if(validationCode == input) phoneNumValid = true
        updateButtonState()
    }

    fun updateButtonState() {
        if (name.isNotBlank() && nickName.isNotBlank() && checkPwdValid && checkUserNameValid && phoneNumValid) {
            checkAllAllowed = true
            buttonColor = PrimaryBlue
        } else {
            checkAllAllowed = false
            buttonColor = LightGray
        }
    }


    // 휴대폰 인증


    // 회원가입

}