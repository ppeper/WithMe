package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.usecase.register.UpdateProfileImgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "싸피"
@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val updateProfileImgUseCase: UpdateProfileImgUseCase
): ViewModel() {
    var profileImg by mutableStateOf("")
    fun updateProfileImg(input: String) = viewModelScope.launch {
        updateProfileImgUseCase.invoke(input)
//        Log.d(TAG, "updatePassword: $password")
    }
}