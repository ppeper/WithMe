package com.bonobono.data.remote

import com.bonobono.data.model.character.response.UserCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character/user/list")
    suspend fun getUserCharacterList(@Query(value = "memberId") memberId: Int) : List<UserCharacterResponse>
}