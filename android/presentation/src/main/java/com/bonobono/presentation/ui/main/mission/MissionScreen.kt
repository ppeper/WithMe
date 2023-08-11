package com.bonobono.presentation.ui.main.mission

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bonobono.domain.model.mission.TotalScore
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.GameNav
import com.bonobono.presentation.ui.QuizNav
import com.bonobono.presentation.ui.common.button.PrimaryButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.CircularProgressBar
import com.bonobono.presentation.ui.main.component.LargeSquareCardWithAnimation
import com.bonobono.presentation.ui.main.component.LinearProgressBar
import com.bonobono.presentation.ui.common.LottieLoader
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.viewmodel.MissionViewModel

private const val TAG = "MissionScreen"
@Composable
fun MissionScreen(navController: NavHostController, missionViewModel: MissionViewModel = hiltViewModel()) {
    missionViewModel.removeCompletedTime() // 하루 지나면 삭제
    val completedTimeOX = missionViewModel.getCompletedTime(Constants.OX_QUIZ)
    val completedTimeFour = missionViewModel.getCompletedTime(Constants.FOUR_QUIZ)
    val completedGame = missionViewModel.getCompletedTime(Constants.GAME)
    val completedTimeAttendance = missionViewModel.getCompletedTime(Constants.ATTENDANCE)

    val totalScore by missionViewModel.totalScore.collectAsState()

    var attendanceIsEnabled = remember { mutableStateOf(completedTimeAttendance == 0L) }
    var gameIsEnabled = remember { mutableStateOf(completedGame == 0L) }
    var quizIsEnabled = remember {
        mutableStateOf(completedTimeOX == 0L && completedTimeFour == 0L)
    }

    LaunchedEffect(Unit) {
        missionViewModel.getScore(1)
    }

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {
        UserInformationItem(totalScore)
        Spacer(modifier = Modifier.size(12.dp))
        DailyGameItem(
            R.raw.animation_check,
            stringResource(R.string.attendance_guide),
            stringResource(R.string.attendance_btn),
            attendanceIsEnabled
        ) {
            missionViewModel.postAttendance(1)
            missionViewModel.putCompletedTime(Constants.ATTENDANCE, System.currentTimeMillis())
            attendanceIsEnabled.value = false
        }
        Spacer(modifier = Modifier.size(12.dp))
        DailyGameItem(
            R.raw.game,
            stringResource(R.string.game_guide),
            stringResource(R.string.game_btn),
            gameIsEnabled,
        ) {
            navController.navigate(GameNav.route)
        }
        Spacer(modifier = Modifier.size(12.dp))
        if (completedTimeOX == 0L) {
            LargeSquareCardWithAnimation(
                R.raw.animation_ox_quiz_card,
                stringResource(R.string.ox_quiz_guide)
            ) {
                navController.navigate("${QuizNav.route}/${Constants.OX_QUIZ}")
            }
        } else if(completedTimeFour == 0L) {
            LargeSquareCardWithAnimation(
                R.raw.animation_four_quiz_card,
                stringResource(R.string.four_quiz_guide)
            ) {
                navController.navigate("${QuizNav.route}/${Constants.FOUR_QUIZ}")
            }
        } else {
            LargeSquareCardWithAnimation(
                R.raw.animation_four_quiz_card,
                stringResource(R.string.four_quiz_guide),
                quizIsEnabled
            ) {
                navController.navigate(QuizNav.route)
            }
        }
    }
}


@Composable
fun DailyGameItem(
    source: Int,
    content: String,
    buttonText: String,
    isEnabled: MutableState<Boolean>,
    onClick: () -> Unit
) {
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

            PrimaryButton(buttonText, Modifier, isEnabled) {
                onClick()
            }
        }
    }
}

@Composable
fun UserInformationItem(totalScore: TotalScore) {
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
                CircularProgressBar(percent = totalScore.attendanceScore * 0.01f, "출셕율")
                Spacer(modifier = Modifier.size(24.dp))
                CircularProgressBar(percent = totalScore.totalScore * 0.01f , "미션달성율")
            }
        }
        LinearProgressBar(
            source = R.drawable.ic_check,
            title = stringResource(R.string.exp_txt),
            percent = 0.3f
        )
    }
}

@Preview
@Composable
fun PreviewMissionScreen() {
    MissionScreen(navController = rememberNavController())
}