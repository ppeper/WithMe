package com.bonobono.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        private const val MINUTE = 60L
        private const val HOUR = 3600L
        private const val DAY = 86400L
        private const val MONTH = 2592000L
        private const val YEAR = 31536000L

        fun dateToString(upload: Date): String {
            val currentTime = System.currentTimeMillis()
            val serverTime = upload.time
            val timeDifference = (currentTime - serverTime) / 1000

            return when {
                timeDifference < 1 -> "방금 전"
                timeDifference < MINUTE -> "${timeDifference}초 전"
                timeDifference < HOUR -> "${timeDifference / MINUTE}분 전"
                timeDifference < DAY -> "${timeDifference / HOUR}시간 전"
                timeDifference < MONTH -> "${timeDifference / DAY}일 전"
                timeDifference < YEAR -> "${timeDifference / MONTH}달 전"
                else -> "${timeDifference / YEAR}년 전"
            }
        }
    }
}