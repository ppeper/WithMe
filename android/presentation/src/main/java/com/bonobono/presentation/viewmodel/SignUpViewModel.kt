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
    var checkPwdValid by mutableStateOf(false)
        private set
    var checkAllAllowed by mutableStateOf(false)
        private set
    var buttonColor by mutableStateOf(LightGray)
        private set

    var checkUserNameValid by mutableStateOf(false)
        private set
    var phoneNumValid by mutableStateOf(false)
        private set
    var passwordSupportTxt by mutableStateOf("")
        private set

    // 아이디 값 입력 및 유효성 확인
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

    // 비밀번호 & 비밀번호 확인 값 받기 && 둘이 일치하는지 확인
    var password by mutableStateOf("")
    fun updatePassword(input: String) {
        password = input
        checkPasswordValidty()
    }

    var passwordCheck by mutableStateOf("")
    fun updatePasswordCheck(input: String) {
        passwordCheck = input
        checkPasswordValidty()
    }

    fun checkPasswordValidty() {
        if (password.isNotEmpty() && passwordCheck.isNotEmpty()) {
            // 둘 다 값이 있는 경우 비밀번호 일치 확인
            checkPwdValid = password == passwordCheck
            if (checkPwdValid) passwordSupportTxt = "비밀번호가 일치합니다" else passwordSupportTxt =
                "비밀번호가 일치하지 않습니다"
        } else {
            checkPwdValid = false
            passwordSupportTxt = ""
        }
    }

    // 휴대폰 번호 입력 && 휴대폰 인증 코드 전송
    var phoneNum by mutableStateOf("")
    fun updatePhoneNum(input: String) {
        phoneNum = input
    }

    // 인증코드 발급 && 인증 코드 입력 시 받은 인증코드와 같은지 확인
    var validationCode by mutableStateOf("")
//    fun updateValidationCode(input: String) {
//        // 인증 번호는 서버에서 받은 인증 코드랑 같은지 비교
//        if (validationCode == input) phoneNumValid = true
//        updateButtonState()
//    }
    fun updateValidationCode(input: String) {
        // 인증 번호는 서버에서 받은 인증 코드랑 같은지 비교
        validationCode == input
        phoneNumValid = true
        updateButtonState()
    }
    fun checkPhoneValidation() {

    }

    // 회원가입 정보 전부 기입 및 인증 전부 확인 시 버튼 활성화
    fun updateButtonState() {
        if (name.isNotBlank() && nickName.isNotBlank() && checkPwdValid && checkUserNameValid && phoneNumValid) {
            checkAllAllowed = true
            buttonColor = PrimaryBlue
        } else {
            checkAllAllowed = false
            buttonColor = LightGray
        }
    }

    // 회원가입
    fun doSignUP() {

    }

}