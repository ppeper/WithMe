package com.bonobono.domain.usecase.register

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.NickName
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class CheckNickNameUseCase @Inject constructor(
    private val registerRepository : RegisterRepository
) {
    suspend operator fun invoke(nickName : NickName) : NetworkResult<Boolean> {
        return registerRepository.checkNickName(nickName)
    }
}