package com.bonobono.data.remote

import com.bonobono.data.model.mission.response.MissionResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface MissionService {
    @POST("quiz/ox")
    suspend fun getOXQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("quiz/four_quiz")
    suspend fun getFourQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("quiz/four_quiz")
    suspend fun getMiniGame(@Query(value = "memberId") memberId: Int) : MissionResponse
}