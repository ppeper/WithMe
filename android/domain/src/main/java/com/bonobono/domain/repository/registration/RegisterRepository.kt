package com.bonobono.domain.repository.registration

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.NickName
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.model.registration.UserName

interface RegisterRepository {

    // 아이디 중복 확인
    suspend fun checkUserName(username: UserName): NetworkResult<Boolean>

    // 닉네임 중복 확인
    suspend fun checkNickName(nickname : NickName) : NetworkResult<Boolean>

    // 회원가입
    suspend fun signUp(register: Register) : NetworkResult<Member>

    // 로그인
    suspend fun login(loginInput: LoginInput) : NetworkResult<LoginResult>

    // 자동 로그인

    // 토큰 값 저장
    suspend fun putLoginResult(loginResult: LoginResult)

    // 사용자 정보 저장
    suspend fun putMemberInfo(member: Member)

    // 아이디, 비밀번호 저장
    suspend fun putLoginInfo(loginInput: LoginInput)

    // sharedpreference에 아이디, 비밀번호 정보 저장
    suspend fun getLoginInfo() : LoginInput

    // firebase fcm token 발급
    suspend fun getFcmToken() : String

    suspend fun deleteLoginInfo()
}