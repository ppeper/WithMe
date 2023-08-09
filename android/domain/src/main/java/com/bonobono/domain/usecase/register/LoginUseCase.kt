package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class LoginUseCase  @Inject constructor(
    private val registerRepository : RegisterRepository
){
    suspend operator fun invoke(member: Member) {
        registerRepository.login(member)
    }
}