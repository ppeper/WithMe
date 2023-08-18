package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bonobono.domain.usecase.local.GetMemberIdUseCase
import com.bonobono.domain.usecase.local.GetRoleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedLocalViewModel @Inject constructor(
    private val getMemberIdUseCase: GetMemberIdUseCase,
    private val getRoleUseCase: GetRoleUseCase
): ViewModel() {

    fun getMemberId(key: String): Int = getMemberIdUseCase.execute(key)
    fun getRole(key: String): String? = getRoleUseCase.execute(key)
}