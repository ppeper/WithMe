package com.bonobono.presentation.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Role
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.usecase.register.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
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



    fun login() = viewModelScope.launch {
        Log.d(TAG, "doLogin: 로그인 버튼 선택")
        val role = Role("USER")
        val register = Register(0, "test","test",password, "a", "010-1234-5678", listOf(role), username)
        loginUseCase.invoke(register)
        Log.d(TAG, "login: ")
    }


    var autoLoginState by mutableStateOf(false)
        private set
    fun updateAutoLoginState(input : Boolean) {
        Log.d(TAG, "updateAutoLoginState: $input")
        autoLoginState = input
    }
}