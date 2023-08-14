package com.bonobono.data.repository

import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.domain.repository.SharedLocalRepository
import javax.inject.Inject

class SharedLocalRepositoryImpl @Inject constructor(
    private val preferenceDatasource: PreferenceDataSource
): SharedLocalRepository {
    override fun getMemberId(key: String): Int = preferenceDatasource.getInt("member_id")

    override fun getRole(key: String): String? = preferenceDatasource.getString("role")

}