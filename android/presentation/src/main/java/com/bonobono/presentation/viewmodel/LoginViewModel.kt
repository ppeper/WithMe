package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.usecase.register.DeleteLoginInfoUseCase
import com.bonobono.domain.usecase.register.GetFCMTokenUseCase
import com.bonobono.domain.usecase.register.GetLoginInfoUseCase
import com.bonobono.domain.usecase.register.GetMemberUseCase
import com.bonobono.domain.usecase.register.LoginResultInputUseCase
import com.bonobono.domain.usecase.register.LoginUseCase
import com.bonobono.domain.usecase.register.LogoutUseCase
import com.bonobono.domain.usecase.register.MemberInfoInputUseCase
import com.bonobono.domain.usecase.register.PutLoginInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val loginResultInputUseCase: LoginResultInputUseCase,
    private val memberInfoInputUseCase: MemberInfoInputUseCase,
    private val getMemberUseCase: GetMemberUseCase,
    private val putLoginInfoUseCase: PutLoginInfoUseCase,
    private val getLoginInfoUseCase: GetLoginInfoUseCase,
    private val getFCMTokenUseCase: GetFCMTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val deleteLoginInfoUseCase: DeleteLoginInfoUseCase
) : ViewModel() {
    var username by mutableStateOf("")
        private set

    fun updateUserName(input: String) {
        username = input
        Log.d(TAG, "updateUserName: $username")
    }

    var password by mutableStateOf("")
    fun updatePassword(input: String) {
        password = input
        Log.d(TAG, "updatePassword: $password")
    }

    var fcmToken by mutableStateOf("")
        private set
    fun getFcmToken() = viewModelScope.launch {
        fcmToken = getFCMTokenUseCase.invoke()
        Log.d(TAG, "getFcmToken: $fcmToken")
    }

    suspend fun login(): String {
        val getFcmTokenJob = viewModelScope.async {
            getFcmToken()
        }
        // fcmToken 가져오기를 기다림
        getFcmTokenJob.await()

        val loginInput = LoginInput(fcmtoken = fcmToken, username = username, password = password)
        val result = loginUseCase.invoke(loginInput)
        return when (result) {
            is NetworkResult.Success -> {
                // token 값 sharedpreference에 넣어주자
                Log.d(TAG, "login: ${result.data}")
                loginResultInputUseCase.invoke(result.data)
//                putMemberInfo()
                "SUCCESS" // Return the login result
            }
            else -> {
                "FAIL" // Return the login result
            }
        }
    }

//    fun putMemberInfo() = viewModelScope.launch {
//        val memberInfo = getMemberUseCase.invoke()
//        if (memberInfo is NetworkResult.Success) {
//            memberInfoInputUseCase.invoke(memberInfo.data)
//        }
//    }

    var autoLoginState by mutableStateOf(false)
        private set

    fun updateAutoLoginState(input: Boolean) {
        Log.d(TAG, "updateAutoLoginState: $input")
        autoLoginState = input
    }

    fun putLoginInfo() = viewModelScope.launch {
        putLoginInfoUseCase.invoke(LoginInput(fcmtoken = fcmToken, username = username, password = password))
    }

    fun getLoginInfo() = viewModelScope.launch {
        val result = getLoginInfoUseCase.invoke()
        username = result.username
        password = result.password
        fcmToken = result.fcmtoken
        Log.d(TAG, "getLoginInfo: $username , $password , $fcmToken")
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase.invoke()
        deleteLoginInfoUseCase.invoke()
    }

}