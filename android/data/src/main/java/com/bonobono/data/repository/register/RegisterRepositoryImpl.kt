package com.bonobono.data.repository.register

import android.util.Log
import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.RegisterService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.repository.registration.RegisterRepository
import javax.inject.Inject

private const val TAG = "싸피"
class RegisterRepositoryImpl @Inject constructor(
    private val  preferenceDatasource: PreferenceDataSource,
    private val registerService: RegisterService
) : RegisterRepository {
    override suspend fun updateMember(member: Member): NetworkResult<Member> {
        return handleApi { registerService.updateMember(member).toDomain() }
    }

    override suspend fun updatePassword(password: Password): NetworkResult<Member> {
        return handleApi { registerService.passwordChange(password).toDomain() }
    }

    override suspend fun checkUserName(username: Member): NetworkResult<String> {
        return handleApi { registerService.checkUserName(username) }
    }

    override suspend fun checkNickName(nickname: Member): NetworkResult<String> {
        return handleApi { registerService.checkNickName(nickname) }
    }

    override suspend fun signUp(register: Register): NetworkResult<Member> {
        return handleApi { registerService.signUp(register).toDomain() }
    }

    override suspend fun login(register: Register) {
        val token = registerService.login(register).toDomain()
        Log.d(TAG, "login: accesstoken : ${token.accessToken} / accesstokenExpire : ${token.accessTokenExpiresIn} / refresh token: ${token.refreshToken}  / grant type : ${token.grantType}")
        preferenceDatasource.putString("access_token", token.accessToken)
        Log.d(TAG, "login: 토큰 들어갔을까?? ${preferenceDatasource.getString("access_token")}")
    }

    override suspend fun logout(): NetworkResult<String> {
        return handleApi { registerService.logout() }
    }

    override suspend fun reissue(token: Token): NetworkResult<Token> {
        return handleApi { registerService.reissue(token).toDomain() }
    }

    override suspend fun getMember(): NetworkResult<Member> {
        return handleApi { registerService.getMember().toDomain() }
    }

    override suspend fun deleteMember(): NetworkResult<String> {
        return handleApi { registerService.deleteMember() }
    }
}