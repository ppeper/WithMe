package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.ProfileImgResult
import com.bonobono.domain.repository.MemberRepository
import javax.inject.Inject

class GetProfileImgUseCase @Inject constructor(
    private val memberRepository: MemberRepository
){
    suspend operator fun invoke() : NetworkResult<ProfileImgResult> {
        return memberRepository.getProfileImg()
    }
}