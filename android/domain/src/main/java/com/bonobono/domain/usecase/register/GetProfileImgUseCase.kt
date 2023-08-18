package com.bonobono.domain.usecase.register

import android.util.Log
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.ProfileImgResult
import com.bonobono.domain.repository.MemberRepository
import javax.inject.Inject

private const val TAG = "싸피"
class GetProfileImgUseCase @Inject constructor(
    private val memberRepository: MemberRepository
){
    suspend operator fun invoke() : NetworkResult<ProfileImgResult> {
        val result = memberRepository.getProfileImg()
        Log.d(TAG, "invoke: ${result}")
        return result
    }
}