package com.bonobono.data.remote

import com.bonobono.data.model.character.request.MainCharacterRequest
import com.bonobono.data.model.character.response.OurCharacterResponse
import com.bonobono.data.model.character.response.UserCharacterResponse
import com.bonobono.domain.model.character.SaveCharacter
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character/user/list")
    suspend fun getUserCharacterList(@Query(value = "memberId") memberId: Int): List<UserCharacterResponse>

    @GET("character/our/list")
    suspend fun getOurCharacterList(@Query(value = "memberId") memberId: Int): List<OurCharacterResponse>

    @PATCH("catch/UserCharacter/save")
    suspend fun patchSaveCharacter(@Body saveCharacter: SaveCharacter)

    @POST("character/user/{character_id}")
    suspend fun getCharacter(@Path(value = "character_id") character_id: Int): UserCharacterResponse

    @PATCH("character/user/main/{character_id}")
    suspend fun patchMainCharacter(
        @Path(value = "character_id") chardacter_id: Int,
        @Body mainCharacterRequest: MainCharacterRequest
    )
}