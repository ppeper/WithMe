package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bonobono.domain.usecase.preference.GetBooleanUseCase
import com.bonobono.domain.usecase.preference.PutBooleanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooleanUseCase: GetBooleanUseCase,
    private val putBooleanUseCase: PutBooleanUseCase
) : ViewModel() {

    fun putBoolean(key: String, value: Boolean) {
        putBooleanUseCase.invoke(key, value)
    }

    fun getBoolean(key: String) : Boolean {
        return getBooleanUseCase.invoke(key)
    }
}