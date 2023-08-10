package com.bonobono.domain.usecase.register

import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val registerRepository : RegisterRepository
) {
    suspend operator fun invoke() {
        registerRepository.logout()
    }
}