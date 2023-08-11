package com.bonobono.di

import android.content.Context
import com.bonobono.BuildConfig
import com.bonobono.data.interceptor.XAccessTokenInterceptor
import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.MissionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(xAccessTokenInterceptor: XAccessTokenInterceptor): OkHttpClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(xAccessTokenInterceptor) // JWT 자동 헤더 전송
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(xAccessTokenInterceptor) // JWT 자동 헤더 전송
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

    @Singleton
    @Provides
    fun provideCommunityService(okHttpClient: OkHttpClient): CommunityService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CommunityService::class.java)

    @Singleton
    @Provides
    fun provideMissionService(okHttpClient: OkHttpClient): MissionService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MissionService::class.java)

    @Singleton
    @Provides
    fun providesRegisterService(okHttpClient: OkHttpClient) : RegisterService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterService::class.java)
}