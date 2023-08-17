package com.bonobono.di

import com.bonobono.BuildConfig
import com.bonobono.data.interceptor.XAccessTokenInterceptor
import com.bonobono.data.remote.CharacterService
import com.bonobono.data.remote.ChatService
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.MapService
import com.bonobono.data.remote.MemberService
import com.bonobono.data.remote.MissionService
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
    fun provideOkHttpClient(xAccessTokenInterceptor: XAccessTokenInterceptor): OkHttpClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(xAccessTokenInterceptor) // JWT 자동 헤더 전송
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(xAccessTokenInterceptor) // JWT 자동 헤더 전송
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

    @Singleton
    @Provides
    fun provideCharacterService(okHttpClient: OkHttpClient): CharacterService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideMapService(okHttpClient: OkHttpClient): MapService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MapService::class.java)

    @Singleton
    @Provides
    fun provideChatService(okHttpClient: OkHttpClient): ChatService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ChatService::class.java)

    @Singleton
    @Provides
    fun provideMemberService(okHttpClient: OkHttpClient): MemberService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MemberService::class.java)
}