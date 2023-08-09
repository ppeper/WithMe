package com.bonobono.di

import com.bonobono.BuildConfig
import com.bonobono.data.interceptor.XAccessTokenInterceptor
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideOkHttpClient(): OkHttpClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
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
    fun providesRegisterService(okHttpClient: OkHttpClient) : RegisterService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RegisterService::class.java)

}