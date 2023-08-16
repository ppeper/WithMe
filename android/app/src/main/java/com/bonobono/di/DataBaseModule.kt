package com.bonobono.di

import android.content.Context
import androidx.room.Room
import com.bonobono.data.local.db.NotificationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideNotificationDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            NotificationDatabase::class.java,
            "notification_db"
        ).build()

    @Singleton
    @Provides
    fun provideNotificationDao(database: NotificationDatabase) = database.notificationDao()
}