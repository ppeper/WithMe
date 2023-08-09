package com.bonobono.domain.usecase.register

import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class CheckNickNameUseCase @Inject constructor(
    private val registerRepository : RegisterRepository
) {
    suspend operator fun invoke(nickName : String) {
        registerRepository.checkNickName(nickName)
    }
}