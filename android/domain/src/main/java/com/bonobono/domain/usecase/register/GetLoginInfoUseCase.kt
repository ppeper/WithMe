package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class GetLoginInfoUseCase@Inject constructor(
    private val registerRepository : RegisterRepository
){
    suspend operator fun invoke() : LoginInput {
        return registerRepository.getLoginInfo()
    }
}