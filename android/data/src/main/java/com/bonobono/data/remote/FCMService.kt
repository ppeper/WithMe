package com.bonobono.data.remote

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bonobono.data.Constants
import com.bonobono.domain.model.notification.Notification
import com.bonobono.domain.repository.NotificationRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

const val TAG = "FCM Token"
class FCMService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationRepository: NotificationRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM Token", token)
        // 토큰을 서버로 전송 또는 필요한 처리 수행
    }

    // Foreground, Background 모두 처리하기 위해서는 data에 값을 담아서 넘긴다.
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var messageTitle = ""
        var messageBody = ""

        // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
        val data = remoteMessage.data
        Log.d(TAG, "data.message: ${data[Constants.TITLE]}")
        Log.d(TAG, "data.message: ${data[Constants.BODY]}")
        messageTitle = data[Constants.TITLE].toString()
        messageBody = data[Constants.BODY].toString()
        val summaryNotification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
            .setSmallIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setGroupSummary(true)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
            }
            notify(101, summaryNotification.build())
        }

//        insertNotification(messageTitle, messageBody)
    }


    private fun insertNotification(
        messageTitle: String,
        messageBody: String
    ) {
        val newNotification = Notification(
            title = messageTitle,
            body = messageBody,
            receiveTime = LocalDateTime.now().toString())
        CoroutineScope(Dispatchers.IO).launch {
            notificationRepository.insertNotification(newNotification)
        }
    }

}