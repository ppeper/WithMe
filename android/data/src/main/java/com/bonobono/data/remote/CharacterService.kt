package com.bonobono.data.remote

import com.bonobono.data.model.character.response.OurCharacterResponse
import com.bonobono.data.model.character.response.UserCharacterResponse
import com.bonobono.data.model.map.response.CampaignResponse
import com.bonobono.data.model.map.response.RankingResponse
import com.bonobono.domain.model.map.Campaign
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character/user/list")
    suspend fun getUserCharacterList(@Query(value = "memberId") memberId: Int) : List<UserCharacterResponse>

    @GET("character/our/list")
    suspend fun getOurCharacterList(@Query(value = "memberId") memberId: Int) : List<OurCharacterResponse>
}