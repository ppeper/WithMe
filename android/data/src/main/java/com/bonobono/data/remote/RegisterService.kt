package com.bonobono.data.remote

import com.bonobono.data.model.registration.response.LoginResponse
import com.bonobono.data.model.registration.response.MemberResponse
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.NickName
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.UserName
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface RegisterService {
    @POST("member/signup")
    suspend fun signUp(
        @Body register: Register
    ) : MemberResponse

    @POST("member/username")
    suspend fun checkUserName(
        @Body username : UserName
    ) : Boolean

    @POST("member/nickname")
    suspend fun checkNickName(
        @Body nickname : NickName
    ) : Boolean

    @POST("member/login")
    suspend fun login(
        @Body loginInput: LoginInput
    ) : LoginResponse

}