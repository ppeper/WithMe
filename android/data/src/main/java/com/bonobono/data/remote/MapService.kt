package com.bonobono.data.remote

import com.bonobono.data.model.map.response.CampaignResponse
import com.bonobono.data.model.map.response.CatchCharacterResponse
import com.bonobono.data.model.map.response.LocationResponse
import com.bonobono.data.model.map.response.RankingResponse
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.CatchKey
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MapService {

    @GET("location")
    suspend fun getLocations(): List<LocationResponse>

    @GET("reward/{locationId}")
    suspend fun getRanking(@Path(value = "locationId") locationId: Long): List<RankingResponse>

    @GET("campaign/{locationId}")
    suspend fun getCampaign(@Path(value = "locationId") locationId: Long): List<CampaignResponse>

    @POST("campaign")
    suspend fun postCampaign(@Body campaign: Campaign)

    @POST("catch/list")
    suspend fun getCatchList(@Body key: CatchKey): List<CatchCharacterResponse>

}