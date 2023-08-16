package com.bonobono.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonobono.data.model.notification.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notifications ORDER BY receiveTime DESC")
    fun getAllNotifications() : Flow<List<NotificationEntity>>

    @Query("SELECT * FROM notifications where id = (:id)")
    suspend fun getNotification(id: Long) : NotificationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationEntity)

    @Query("DELETE FROM notifications where id = (:id)")
    suspend fun deleteNotification(id: Int)
}
