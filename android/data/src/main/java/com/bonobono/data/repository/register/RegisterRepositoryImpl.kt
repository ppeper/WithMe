package com.bonobono.data.repository.register

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.RegisterService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerService: RegisterService
) : RegisterRepository {
    override suspend fun updateMember(member: Member): NetworkResult<Member> {
//        return handleApi { registerService.updateMember(member).toDomain() }?\
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(password: Password): NetworkResult<Member> {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserName(username: String): NetworkResult<String> {
        TODO("Not yet implemented")
    }

    override suspend fun checkNickName(nickname: String): NetworkResult<String> {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(member: Member): NetworkResult<Member> {
        TODO("Not yet implemented")
    }

    override suspend fun login(member: Member): NetworkResult<Token> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): NetworkResult<String> {
        TODO("Not yet implemented")
    }

    override suspend fun reissue(token: Token): NetworkResult<Token> {
        TODO("Not yet implemented")
    }

    override suspend fun getMember(): NetworkResult<Member> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMember(): NetworkResult<String> {
        TODO("Not yet implemented")
    }
}