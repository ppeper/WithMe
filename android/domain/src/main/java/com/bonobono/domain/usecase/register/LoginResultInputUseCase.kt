package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class LoginResultInputUseCase @Inject constructor(
    private val registerRepository : RegisterRepository
) {
    suspend operator fun invoke(loginResult: LoginResult) {
        registerRepository.putLoginResult(loginResult)
    }
}