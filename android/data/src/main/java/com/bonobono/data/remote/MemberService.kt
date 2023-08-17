package com.bonobono.data.remote

import com.bonobono.data.model.registration.response.MemberProfileResponse
import com.bonobono.data.model.registration.response.MemberResponse
import com.bonobono.data.model.registration.response.ProfileImgResponse
import com.bonobono.data.model.registration.response.TokenResponse
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Token
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface MemberService {
    @PUT("member/update")
    suspend fun updateMember(
        @Body member : Member
    ) : MemberResponse

    @GET("member/profile")
    suspend fun getMember() : MemberProfileResponse

    @PUT("member/password")
    suspend fun passwordChange(
        @Body password : Password
    ) : MemberResponse

    @DELETE("member/profile")
    suspend fun deleteMember() : String

    @GET("member/logout")
    suspend fun logout() : String

    @POST("member/reissue")
    suspend fun reissue(
        @Body token : Token
    ) : TokenResponse

    @Multipart
    @POST("member/img")
    suspend fun updateProfileImg(
        @Part image : MultipartBody.Part
    )

    @GET("member/img")
    suspend fun getProfileImg() : ProfileImgResponse
}