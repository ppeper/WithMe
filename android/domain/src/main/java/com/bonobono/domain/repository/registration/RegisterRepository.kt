package com.bonobono.domain.repository.registration

import com.bonobono.domain.model.NetworkResult
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
    suspend fun login(register: Register)

    // 로그아웃
    suspend fun logout() : NetworkResult<String>

    // 토큰 재발급
    suspend fun reissue(token: Token) : NetworkResult<Token>

    // 회원 정보 가져오기
    suspend fun getMember() : NetworkResult<Member>

    // 회원 정보 삭제
    suspend fun deleteMember() : NetworkResult<String>
}