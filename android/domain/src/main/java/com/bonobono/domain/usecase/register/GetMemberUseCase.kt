package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.ProfileInfo
import com.bonobono.domain.repository.MemberRepository
import javax.inject.Inject

class GetMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke() : NetworkResult<ProfileInfo> {
        return memberRepository.getMember()
    }
}