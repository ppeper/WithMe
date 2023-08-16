package com.bonobono.data.repository

import com.bonobono.data.mapper.Converter
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.MemberService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberService: MemberService
) : MemberRepository {
    override suspend fun updateMember(member: Member): NetworkResult<Member> {
        return handleApi { memberService.updateMember(member).toDomain() }
    }

    override suspend fun updatePassword(password: Password): NetworkResult<Member> {
        return handleApi { memberService.passwordChange(password).toDomain() }
    }

    override suspend fun logout(): NetworkResult<String> {
        return handleApi { memberService.logout() }
    }

    override suspend fun reissue(token: Token): NetworkResult<Token> {
        return handleApi { memberService.reissue(token).toDomain() }
    }

    override suspend fun getMember(): NetworkResult<Member> {
        return handleApi { memberService.getMember().toDomain() }
    }

    override suspend fun deleteMember(): NetworkResult<String> {
        return handleApi { memberService.deleteMember() }
    }

    override suspend fun updateProfileImg(img : String): NetworkResult<Unit> {
        val image = Converter.createMultipartBodyPart(img)
        return handleApi { memberService.updateProfileImg(image) }
    }
}