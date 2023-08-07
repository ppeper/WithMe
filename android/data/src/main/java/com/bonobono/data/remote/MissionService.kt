package com.bonobono.data.remote

import com.bonobono.data.model.mission.response.MissionResponse
import com.bonobono.data.model.mission.response.TotalScoreResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MissionService {
    @POST("quiz/ox")
    suspend fun getOXQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("quiz/four_quiz")
    suspend fun getFourQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("quiz/four_quiz")
    suspend fun getMiniGame(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("attendance")
    suspend fun postAttendance(@Query(value = "memberId") memberId: Int)

    @GET("dailymission")
    suspend fun getTotalScore(@Query(value = "memberId") memberId: Int) : TotalScoreResponse
}