package com.bonobono.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bonobono.data.local.dao.NotificationDao
import com.bonobono.data.model.notification.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1)
abstract class NotificationDatabase: RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}
