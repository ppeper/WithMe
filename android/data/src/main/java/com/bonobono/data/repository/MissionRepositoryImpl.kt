package com.bonobono.data.repository

import com.bonobono.data.utils.Constants
import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.MissionService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.MiniGame
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore
import com.bonobono.domain.repository.MissionRepository
import java.util.Calendar
import javax.inject.Inject

class MissionRepositoryImpl @Inject constructor(
    private val preferenceDatasource: PreferenceDataSource,
    private val missionService: MissionService
) : MissionRepository {

    override suspend fun getOXQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getOXQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun postIsSuccessOXQuiz(isSuccess: IsSuccess) : NetworkResult<Boolean> {
        return handleApi { missionService.postOXQuizIsSuccess(isSuccess) }
    }

    override suspend fun getFourQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getFourQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun postIsSuccessFourQuiz(isSuccess: IsSuccess) : NetworkResult<Boolean> {
        return handleApi { missionService.postFourQuizIsSuccess(isSuccess) }
    }

    override suspend fun getMiniGame(memberId: Int): NetworkResult<MiniGame> {
        return handleApi { missionService.getMiniGame(memberId = memberId).toDomain() }
    }

    override suspend fun postIsSuccessMiniGame(isSuccess: IsSuccess): NetworkResult<Boolean> {
        return handleApi { missionService.postMiniGame(isSuccess) }
    }


    override suspend fun postAttendance(memberId: Int) {
        handleApi { missionService.postAttendance(memberId = memberId) }
    }

    override suspend fun getTotalScore(memberId: Int): NetworkResult<TotalScore> {
        return handleApi { missionService.getTotalScore(memberId = memberId).toDomain() }
    }

    override suspend fun getCatchOXQuiz(memberId: Int): NetworkResult<Mission> {
        return handleApi { missionService.getCatchOXQuiz(memberId = memberId).toDomain() }
    }

    override suspend fun postCatchIsSuccessOXQuiz(isSuccess: IsSuccess) : NetworkResult<Boolean> {
        return handleApi { missionService.postCatchOXQuizIsSuccess(isSuccess) }
    }

    override fun getCompletedTime(key: String): Long {
        return preferenceDatasource.getLong(key)
    }

    override suspend fun putCompletedTime(key: String, time: Long) {
        preferenceDatasource.putLong(key, time)
    }

    override suspend fun removeCompletedTime() {
        val calendar = Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_YEAR)

        val ox = preferenceDatasource.getLong(Constants.OX_QUIZ)
        val four = preferenceDatasource.getLong(Constants.FOUR_QUIZ)
        val attendance = preferenceDatasource.getLong(Constants.ATTENDANCE)

        if (ox != 0L) {
            val oxCalendar = Calendar.getInstance()
            oxCalendar.timeInMillis = ox
            if (oxCalendar.get(Calendar.DAY_OF_YEAR) != currentDay) {
                preferenceDatasource.remove(Constants.OX_QUIZ)
            }
        }

        if (four != 0L) {
            val fourCalendar = Calendar.getInstance()
            fourCalendar.timeInMillis = four
            if (fourCalendar.get(Calendar.DAY_OF_YEAR) != currentDay) {
                preferenceDatasource.remove(Constants.FOUR_QUIZ)
            }
        }

        if (attendance != 0L) {
            val attendanceCalendar = Calendar.getInstance()
            attendanceCalendar.timeInMillis = attendance
            if (attendanceCalendar.get(Calendar.DAY_OF_YEAR) != currentDay) {
                preferenceDatasource.remove(Constants.ATTENDANCE)
            }
        }
    }

    override fun getBoolean(key: String): Boolean {
        return preferenceDatasource.getBoolean(key)
    }

    override fun putBoolean(key: String, value: Boolean) {
        preferenceDatasource.putBoolean(key, value)
    }

}