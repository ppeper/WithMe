package com.bonobono.domain.usecase.register

import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class CheckUserNameUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(userName: String) {
        registerRepository.checkUserName(userName)
    }
}