package com.bonobono.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

private const val TAG = "LoginViewModel"
class LoginViewModel: ViewModel() {
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


    fun doLogin() {
        Log.d(TAG, "doLogin: 로그인 버튼 선택")
    }

    var autoLoginState by mutableStateOf(false)
        private set
    fun updateAutoLoginState(input : Boolean) {
        Log.d(TAG, "updateAutoLoginState: $input")
        autoLoginState = input
    }
}