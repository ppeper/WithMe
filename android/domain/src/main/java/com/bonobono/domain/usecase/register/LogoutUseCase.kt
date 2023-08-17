package com.bonobono.domain.usecase.register

import com.bonobono.domain.repository.MemberRepository
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke() {
        memberRepository.logout()
    }
}