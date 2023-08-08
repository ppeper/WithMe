package com.bonobono.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var username by mutableStateOf("")
        private set

    fun updateUserName(input: String) {
        username = input
    }

    var password by mutableStateOf("")
    fun updatePassword(input: String) {
        password = input
    }


}