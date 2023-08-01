package com.bonobono.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.CircularProgressBar
import com.bonobono.presentation.ui.main.component.LottieLoader
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.main.component.LinearProgressBar
import com.bonobono.presentation.ui.theme.PrimaryBlue
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
            DailyMissionItem(R.raw.mission_2, "O/X 퀴즈 풀고\n경험치 얻기")
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun DailyMissionItem(source: Int, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = content,
                style = CustomTextStyle.missionGuideTextStyle
            )

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LottieLoader(
                    source = source,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .height(this.maxWidth)
                        .padding(horizontal = 40.dp)
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.BottomEnd),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    contentColor = White
                )
            ) {
                Text(text = "미션 해결하기")
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
            horizontalArrangement = Arrangement.Center
        ) {
            ProfilePhoto(profileImage = R.drawable.beluga_whale)
            CircularProgressBar(percent = 0.3f)
            CircularProgressBar(percent = 0.7f)
        }
        LinearProgressBar(source = R.drawable.ic_check, title = "경험치", percent = 0.3f)
    }
}

@Preview
@Composable
fun PreviewMissionScreen() {
    MissionScreen(navController = rememberNavController())
}