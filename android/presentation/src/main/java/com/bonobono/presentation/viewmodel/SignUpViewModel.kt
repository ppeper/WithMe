package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.NickName
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Role
import com.bonobono.domain.model.registration.UserName
import com.bonobono.domain.usecase.register.CheckNickNameUseCase
import com.bonobono.domain.usecase.register.CheckUserNameUseCase
import com.bonobono.domain.usecase.register.SignUpUseCase
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "SignUpViewModel"

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val checkUserNameUseCase: CheckUserNameUseCase,
    private val checkNickNameUseCase: CheckNickNameUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    var checkPwdValid by mutableStateOf(false)
        private set
    var checkAllAllowed by mutableStateOf(false)
        private set
    var buttonColor by mutableStateOf(LightGray)
        private set

    private var phoneNumValid by mutableStateOf(false)

    var passwordSupportTxt by mutableStateOf("")
        private set
    // 아이디 값 입력 및 유효성 확인
    var username by mutableStateOf("")
        private set

    fun updateUserName(input: String) {
        username = input
        checkUserNameState = false
        updateButtonState()
    }

    var checkUserNameState by mutableStateOf(false)
        private set
    var checkNickNameState by mutableStateOf(false)
        private set

    suspend fun checkUserName(): String {
        val result = checkUserNameUseCase.invoke(UserName(username))

        return when (result) {
            is NetworkResult.Success -> {
                Log.d(TAG, "checkUserName: ${result.data}")
                checkUserNameState = true
                Log.d(TAG, "checkUserName: username success")
                "success"
            }
            else -> {
                Log.d(TAG, "checkUserName: username fail")
                "fail"
            }
        }
    }


    suspend fun checkNickName(): String {
        val result = checkNickNameUseCase.invoke(NickName(nickName))

        return when (result) {
            is NetworkResult.Success -> {
                Log.d(TAG, "checkNickName: nickname success")
                checkNickNameState = true
                "success"
            }
            else -> {
                Log.d(TAG, "checkNickName: nickname fail")
                "fail"
            }
        }
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
        checkNickNameState = false
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
        updateButtonState()
    }

    // 휴대폰 번호 입력 && 휴대폰 인증 코드 전송
    var phoneNum by mutableStateOf("")
    fun updatePhoneNum(input: String) {
        phoneNum = input
        updateButtonState()
    }

    // 인증코드 발급 && 인증 코드 입력 시 받은 인증코드와 같은지 확인
    var validationCode by mutableStateOf("")

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
        if (name.isNotBlank() && nickName.isNotBlank() && checkPwdValid && checkNickNameState && checkUserNameState) {
            checkAllAllowed = true
            buttonColor = PrimaryBlue
        } else {
            checkAllAllowed = false
            buttonColor = LightGray
        }
    }

    private val _signUpState = MutableStateFlow<NetworkResult<Member>>(NetworkResult.Loading)
    val signUpState : StateFlow<NetworkResult<Member>> = _signUpState

    fun signUp() = viewModelScope.launch {
        // 비밀번호 check 부분은 값이 없으면 회원가입이 안되므로 임시로 데이터 넣음
        // role도 일단 다 USER로 지정해버림 아니면 회원가입 안됨
        Log.d(TAG, "signUp: password ${password}")
        val role = Role("ADMIN")
        val register = Register(0, name = name, nickname = nickName, password = password,  phoneNumber = "1234", role = listOf(role), username = username)
        Log.d(TAG, "signUp: ${register.toString()}")
        _signUpState.emit(signUpUseCase.invoke(register))
        Log.d(TAG, "signUp: ${signUpState.value}")
    }
}