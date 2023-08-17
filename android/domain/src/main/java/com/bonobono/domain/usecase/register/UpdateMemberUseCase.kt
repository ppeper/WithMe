package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.repository.MemberRepository
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class UpdateMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(member: Member) {
        memberRepository.updateMember(member = member)
    }
}