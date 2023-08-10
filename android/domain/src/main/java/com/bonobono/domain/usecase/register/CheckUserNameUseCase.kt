package com.bonobono.domain.usecase.register

import android.util.Log
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

private const val TAG = "싸피"
class CheckUserNameUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(userName: Member): String {
        return when(val result = registerRepository.checkUserName(userName)) {
            is NetworkResult.Success -> {
                Log.d(TAG, "invoke: ${result.data}")
                result.data
            }
            is NetworkResult.Loading -> {
                "loading"
            }
            else -> {
                "FAIL"
            }
        }
    }
}