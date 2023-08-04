package com.bonobono.presentation.ui.common.text

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.TextGray

object CustomTextStyle {

    val missionGuideTextStyle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Black_100,
    )

    val gameGuideTextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Black_100,
    )

    val mapTitleTextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Black_100,
    )

    val mapGrayTextStyle = TextStyle(
        fontSize = 12.sp,
        color = TextGray,
    )
}