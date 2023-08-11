package com.bonobono.data.remote

import com.bonobono.data.model.map.response.LocationResponse
import retrofit2.http.GET

interface MapService {

    @GET("location")
    suspend fun getLocations() : List<LocationResponse>
}