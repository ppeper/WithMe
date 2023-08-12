package com.bonobono.domain.repository.registration

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Token

interface RegisterRepository {

    // 회원 정보 수정
    suspend fun updateMember(member: Member): NetworkResult<Member>

    // 비밀번호 변경
    suspend fun updatePassword(password : Password): NetworkResult<Member>

    // 아이디 중복 확인
    suspend fun checkUserName(username: Member): NetworkResult<String>

    // 닉네임 중복 확인
    suspend fun checkNickName(nickname : Member) : NetworkResult<String>

    // 회원가입
    suspend fun signUp(register: Register) : NetworkResult<Member>

    // 로그인
    suspend fun login(loginInput: LoginInput) : NetworkResult<LoginResult>

    // 로그아웃
    suspend fun logout() : NetworkResult<String>

    // 토큰 재발급
    suspend fun reissue(token: Token) : NetworkResult<Token>

    // 회원 정보 가져오기
    suspend fun getMember() : NetworkResult<Member>

    // 회원 정보 삭제
    suspend fun deleteMember() : NetworkResult<String>

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
}