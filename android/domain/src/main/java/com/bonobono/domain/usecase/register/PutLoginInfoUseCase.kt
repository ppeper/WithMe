package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class PutLoginInfoUseCase @Inject constructor(
    private val registerRepository : RegisterRepository
){
    suspend operator fun invoke(loginInput: LoginInput) {
        return registerRepository.putLoginInfo(loginInput)
    }
}