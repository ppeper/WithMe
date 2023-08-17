package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.repository.MemberRepository
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class UpdatePasswordUseCase @Inject constructor(
    private val memberRepository: MemberRepository
){
    suspend operator fun invoke(password: Password) {
        memberRepository.updatePassword(password)
    }
}