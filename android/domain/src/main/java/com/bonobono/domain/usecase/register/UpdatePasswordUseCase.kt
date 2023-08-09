package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class UpdatePasswordUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
){
    suspend operator fun invoke(password: Password) {
        registerRepository.updatePassword(password)
    }
}