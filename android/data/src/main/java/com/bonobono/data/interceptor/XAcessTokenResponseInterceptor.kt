package com.bonobono.data.interceptor

import com.bonobono.data.local.PreferenceDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class XAccessTokenResponseInterceptor @Inject constructor(
    private val dataSource: PreferenceDataSource
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

}