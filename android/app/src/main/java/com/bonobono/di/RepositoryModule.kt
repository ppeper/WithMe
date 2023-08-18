package com.bonobono.di

import android.content.Context
import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.local.dao.NotificationDao
import com.bonobono.data.remote.CharacterService
import com.bonobono.data.remote.ChatService
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.MapService
import com.bonobono.data.remote.MemberService
import com.bonobono.data.remote.MissionService
import com.bonobono.data.repository.MissionRepositoryImpl
import com.bonobono.data.remote.RegisterService
import com.bonobono.data.repository.CharacterRepositoryImpl
import com.bonobono.data.repository.ChatRepositoryImpl
import com.bonobono.data.repository.MapRepositoryImpl
import com.bonobono.data.repository.NotificationRepositoryImpl
import com.bonobono.data.repository.MemberRepositoryImpl
import com.bonobono.data.repository.SharedLocalRepositoryImpl
import com.bonobono.data.repository.community.CommunityRepositoryImpl
import com.bonobono.domain.repository.MissionRepository
import com.bonobono.data.repository.register.RegisterRepositoryImpl
import com.bonobono.domain.repository.CharacterRepository
import com.bonobono.domain.repository.MapRepository
import com.bonobono.domain.repository.MemberRepository
import com.bonobono.domain.repository.chatting.ChattingRepository
import com.bonobono.domain.repository.NotificationRepository
import com.bonobono.domain.repository.SharedLocalRepository
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
    fun provideSharedLocalRepository(preferenceDataSource: PreferenceDataSource) : SharedLocalRepository {
        return SharedLocalRepositoryImpl(preferenceDatasource = preferenceDataSource)
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

    @Provides
    @Singleton
    fun provideCharacterRepository(characterService: CharacterService) : CharacterRepository {
        return CharacterRepositoryImpl(characterService)
    }

    @Provides
    @Singleton
    fun provideMapRepository(mapService: MapService) : MapRepository {
        return MapRepositoryImpl(mapService)
    }

    @Provides
    @Singleton
    fun provideChattingRepository(chatService: ChatService) : ChattingRepository {
        return ChatRepositoryImpl(chatService)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(notificationDao: NotificationDao): NotificationRepository {
        return NotificationRepositoryImpl(notificationDao)
    }

    @Provides
    @Singleton
    fun provideMemberRepository(memberService: MemberService) : MemberRepository {
        return MemberRepositoryImpl(memberService)
    }
}