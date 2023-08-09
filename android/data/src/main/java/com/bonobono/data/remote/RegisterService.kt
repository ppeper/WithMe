package com.bonobono.data.remote

import com.bonobono.data.model.registration.request.MemberRequest
import com.bonobono.data.model.registration.request.PasswordChangeRequest
import com.bonobono.data.model.registration.request.TokenRequest
import com.bonobono.data.model.registration.response.MemberResponse
import com.bonobono.data.model.registration.response.TokenResponse
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Token
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface RegisterService {

    @PUT("member/update")
    suspend fun updateMember(
        body: MemberRequest
    ) : MemberResponse

    @POST("member/signup")
    suspend fun signUp(
        @Body body : MemberRequest
    ) : MemberResponse

    @POST("member/username")
    suspend fun checkUserName(
        @Body body : MemberRequest
    ) : String

    @POST("member/nickname")
    suspend fun checkNickName(
        body : MemberRequest
    ) : String

    @POST("member/login")
    suspend fun login(
        @Body body : MemberRequest
    ) : TokenResponse

    @POST("member/reissue")
    suspend fun reissue(
        @Body body : TokenRequest
    ) : TokenResponse

    @GET("member/profile")
    suspend fun getMember() : MemberResponse

    @PUT("member/password")
    suspend fun passwordChange(
        @Body body : PasswordChangeRequest
    ) : MemberResponse

    @DELETE("member/profile")
    suspend fun deleteMember() : String
    @GET("member/logout")
    suspend fun logout() : String
}