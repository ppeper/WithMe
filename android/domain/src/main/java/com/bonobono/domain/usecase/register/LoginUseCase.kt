package com.bonobono.domain.usecase.register

import android.util.Log
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

private const val TAG = "싸피"
class LoginUseCase  @Inject constructor(
    private val registerRepository : RegisterRepository
){
    suspend operator fun invoke(loginInput: LoginInput) : NetworkResult<LoginResult> {
        Log.d(TAG, "invoke: ${loginInput}")
        return registerRepository.login(loginInput)
    }
}