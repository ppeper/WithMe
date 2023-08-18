package com.bonobono.presentation.ui.common.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.ui.theme.wanju_daedunsan

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

    val quizTitleStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.ramche)),
        fontSize = 24.sp,
        lineHeight = 1.5.em
    )

    val quizContentStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.ramche)),
        fontSize = 14.sp,
        lineHeight = 1.5.em
    )

    val primaryColorBtnText = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = White
    )

    val bonobonoTFText = TextStyle(
        fontSize = 16.sp,
        color = TextGray
    )

    val loginDarkGrayText = TextStyle(
        fontSize = 12.sp,
        color = DarkGray
    )

    val appNameText = TextStyle(
        fontSize = 48.sp,
        fontFamily = wanju_daedunsan,
        fontWeight = FontWeight.Bold,
        color = PrimaryBlue
    )
}