package com.bonobono.domain.usecase.register

import android.util.Log
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

private const val TAG = "싸피"
class GetFCMTokenUseCase @Inject constructor(
    private val registerRepository : RegisterRepository
) {
    suspend operator fun invoke() : String {
        return registerRepository.getFcmToken()
    }
}