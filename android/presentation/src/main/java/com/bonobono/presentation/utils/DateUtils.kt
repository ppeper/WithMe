package com.bonobono.presentation.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val MINUTE = 60L
    private const val HOUR = 3600L
    private const val DAY = 86400L
    private const val MONTH = 2592000L
    private const val YEAR = 31536000L

    fun dateToString(timeString: String): String {
        val time = timeString.slice(0..22)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateTime = LocalDateTime.parse(time, formatter)

        val currentTime = LocalDateTime.now()
        val timeDifference = ChronoUnit.SECONDS.between(dateTime, currentTime)

        return when {
            timeDifference < MINUTE -> "방금 전"
            timeDifference < HOUR -> "${timeDifference / MINUTE}분 전"
            timeDifference < DAY -> "${timeDifference / HOUR}시간 전"
            timeDifference < MONTH -> "${timeDifference / DAY}일 전"
            timeDifference < YEAR -> "${timeDifference / MONTH}달 전"
            else -> "${timeDifference / YEAR}년 전"
        }
    }

    fun dateToCampaignString(inputDateStr: String): String {
        return inputDateStr.substring(2, 10).replace("-", ".")
    }

    fun convertMillisToYymmdd(millis: Long): String {
        val dateFormat = SimpleDateFormat("yy.MM.dd")
        val date = Date(millis)
        return dateFormat.format(date)
    }
}