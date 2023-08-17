package com.bonobono.data.remote

import com.bonobono.data.model.mission.response.MiniGameResponse
import com.bonobono.data.model.mission.response.MissionResponse
import com.bonobono.data.model.mission.response.TotalScoreResponse
import com.bonobono.domain.model.mission.IsSuccess
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface MissionService {
    @GET("quiz/ox")
    suspend fun getOXQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse
    @POST("quiz/ox/IsSuccess")
    suspend fun postOXQuizIsSuccess(
        @Body isSuccess: IsSuccess
    ) : Boolean

    @GET("quiz/four_quiz")
    suspend fun getFourQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("quiz/four_quiz/IsSuccess")
    suspend fun postFourQuizIsSuccess(
        @Body isSuccess: IsSuccess
    ) : Boolean

    @GET("minigame")
    suspend fun getMiniGame(@Query(value = "memberId") memberId: Int) : MiniGameResponse

    @POST("minigame/IsSuccess")
    suspend fun postMiniGame(@Body isSuccess: IsSuccess) : Boolean

    @GET("attendance")
    suspend fun postAttendance(@Query(value = "memberId") memberId: Int)

    @GET("dailymission")
    suspend fun getTotalScore(@Query(value = "memberId") memberId: Int) : TotalScoreResponse

    @GET("catch/ox")
    suspend fun getCatchOXQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse
    @POST("catch/ox/IsSuccess")
    suspend fun postCatchOXQuizIsSuccess(
        @Body isSuccess: IsSuccess
    ) : Boolean

}