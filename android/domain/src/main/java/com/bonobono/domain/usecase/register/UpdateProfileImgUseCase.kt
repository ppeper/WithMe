package com.bonobono.domain.usecase.register

import com.bonobono.domain.repository.MemberRepository
import javax.inject.Inject

class UpdateProfileImgUseCase @Inject constructor(
    private val memberRepository: MemberRepository
){
    suspend operator fun invoke(img : String) {
        memberRepository.updateProfileImg(img)
    }
}