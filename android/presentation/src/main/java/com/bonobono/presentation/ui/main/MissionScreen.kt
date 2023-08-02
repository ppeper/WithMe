package com.bonobono.presentation.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.button.PrimaryButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.CircularProgressBar
import com.bonobono.presentation.ui.main.component.LargeSquareCardWithAnimation
import com.bonobono.presentation.ui.main.component.LinearProgressBar
import com.bonobono.presentation.ui.main.component.LottieLoader
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.White

@Composable
fun MissionScreen(navController: NavController) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {
        ;
        UserInformationItem()
        Spacer(modifier = Modifier.size(12.dp))
        DailyGameItem(R.raw.animation_check, "출석체크하고 경험치 받기\nExp.5", "출석하기")
        Spacer(modifier = Modifier.size(12.dp))
        DailyGameItem(R.raw.game, "게임 클리어하고 경험치 받기\nExp.10", "게임하기")
        Spacer(modifier = Modifier.size(12.dp))
        DailyMission()
    }
}

@Composable
fun DailyMission() {
    val items = listOf<String>(
        "1", "2", " 3"
    )
    Column(
        Modifier.padding(vertical = 4.dp)
    ) {
        items.take(1).forEach {
            LargeSquareCardWithAnimation(R.raw.daily_quiz_two, "O/X 퀴즈 풀고\n경험치 얻기")
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun DailyGameItem(source: Int, content: String, buttonText: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White, contentColor = White)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LottieLoader(
                source = source,
                modifier = Modifier.size(48.dp)
            )

            Text(
                text = content,
                style = CustomTextStyle.gameGuideTextStyle,
                modifier = Modifier.weight(1f)
            )

            PrimaryButton(buttonText, Modifier) {

            }
        }
    }
}

@Composable
fun UserInformationItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            ProfilePhoto(
                profileImage = R.drawable.beluga_whale, modifier = Modifier
                    .clip(CircleShape)
                    .background(White)
                    .border(BorderStroke(1.dp, LightGray), shape = CircleShape)
            )
            Row() {
                CircularProgressBar(percent = 0.3f, "출셕율")
                Spacer(modifier = Modifier.size(24.dp))
                CircularProgressBar(percent = 0.7f, "미션달성율")
            }
        }
        LinearProgressBar(source = R.drawable.ic_check, title = "경험치", percent = 0.3f)
    }
}

@Preview
@Composable
fun PreviewMissionScreen() {
    MissionScreen(navController = rememberNavController())
}