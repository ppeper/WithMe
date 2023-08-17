package com.bonobono.domain.repository

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.ProfileImgResult
import com.bonobono.domain.model.registration.ProfileInfo
import com.bonobono.domain.model.registration.Token

interface MemberRepository {
    // 회원 정보 수정
    suspend fun updateMember(member: Member): NetworkResult<Member>

    // 비밀번호 변경
    suspend fun updatePassword(password : Password): NetworkResult<Member>

    // 로그아웃
    suspend fun logout() : NetworkResult<String>

    // 토큰 재발급
    suspend fun reissue(token: Token) : NetworkResult<Token>

    // 회원 정보 가져오기
    suspend fun getMember() : NetworkResult<ProfileInfo>

    // 회원 정보 삭제
    suspend fun deleteMember() : NetworkResult<String>

    // 프로필 이미지 업데이트
    suspend fun updateProfileImg(img : String) : NetworkResult<Unit>

    // 프로필 이미지 가져오기
    suspend fun getProfileImg() : NetworkResult<ProfileImgResult>
}