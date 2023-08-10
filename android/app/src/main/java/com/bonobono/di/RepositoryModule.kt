package com.bonobono.di

import android.content.Context
import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.MissionService
import com.bonobono.data.repository.MissionRepositoryImpl
import com.bonobono.data.remote.RegisterService
import com.bonobono.data.repository.community.CommunityRepositoryImpl
import com.bonobono.domain.repository.MissionRepository
import com.bonobono.data.repository.register.RegisterRepositoryImpl
import com.bonobono.domain.repository.community.CommunityRepository
import com.bonobono.domain.repository.registration.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCommunityRepository(communityService: CommunityService): CommunityRepository {
        return CommunityRepositoryImpl(communityService)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(registerService: RegisterService, preferenceDataSource: PreferenceDataSource) : RegisterRepository {
        return RegisterRepositoryImpl(registerService = registerService, preferenceDatasource = preferenceDataSource)
    }

    @Provides
    @Singleton
    fun provideMissionRepository(
        missionService: MissionService,
        preferenceDataSource: PreferenceDataSource
    ): MissionRepository {
        return MissionRepositoryImpl(
            missionService = missionService,
            preferenceDatasource = preferenceDataSource
        )
    }

    @Provides
    @Singleton
    fun providePreferenceDataSource(@ApplicationContext context: Context) : PreferenceDataSource {
        return PreferenceDataSource(context)
    }
}