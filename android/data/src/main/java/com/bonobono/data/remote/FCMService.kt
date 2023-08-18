package com.bonobono.data.remote

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bonobono.data.R
import com.bonobono.data.utils.Constants
import com.bonobono.data.utils.Constants.TYPE_REPORT
import com.bonobono.domain.model.notification.Notification
import com.bonobono.domain.repository.NotificationRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

const val TAG = "FCM Token"

@AndroidEntryPoint
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
        var articleId = ""
        var type = ""

        // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
        val notification = remoteMessage.notification
        val data = remoteMessage.data
        messageTitle = notification?.title.toString()
        messageBody = notification?.body.toString()
        type = data[Constants.TYPE] ?: TYPE_REPORT
        articleId = if (type == TYPE_REPORT) data[Constants.REPORT_ID].toString() else data[Constants.ARTICLE_ID].toString()

        val mainIntent = Intent(this, Class.forName("com.bonobono.presentation.ui.MainActivity")).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(Constants.TYPE, type)
            putExtra(Constants.ARTICLE_ID, articleId)
        }

        val mainPendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 101, mainIntent,PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)


        val summaryNotification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setContentIntent(mainPendingIntent)
            .setGroupSummary(true)
            .setAutoCancel(true)
            .setFullScreenIntent(mainPendingIntent, true)

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

        insertNotification(type, articleId, messageTitle, messageBody)
    }


    private fun insertNotification(
        type: String,
        articleId: String,
        messageTitle: String,
        messageBody: String
    ) {
        val newNotification = Notification(
            type = type,
            articleId = articleId,
            title = messageTitle,
            body = messageBody,
            receiveTime = LocalDateTime.now().toString())
        CoroutineScope(Dispatchers.IO).launch {
            notificationRepository.insertNotification(newNotification)
        }
    }

}