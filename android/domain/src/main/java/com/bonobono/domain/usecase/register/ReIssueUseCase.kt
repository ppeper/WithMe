package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.repository.MemberRepository
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class ReIssueUseCase  @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(token: Token) {
        memberRepository.reissue(token)
    }
}