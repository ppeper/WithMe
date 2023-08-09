package com.bonobono.presentation.ui.main.mission

import android.util.Log
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
fun MissionScreen(navController: NavHostController, viewModel: MissionViewModel = hiltViewModel()) {
    val completedTimeOX= viewModel.getCompletedTime(Constants.OX_QUIZ)
    val completedTimeFour = viewModel.getCompletedTime(Constants.FOUR_QUIZ)
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {
        ;
        UserInformationItem()
        Spacer(modifier = Modifier.size(12.dp))
        DailyGameItem(
            R.raw.animation_check,
            stringResource(R.string.attendance_guide),
            stringResource(R.string.attendance_btn),
        ) {
            viewModel.postAttendance(1)
        }
        Spacer(modifier = Modifier.size(12.dp))
        DailyGameItem(
            R.raw.game,
            stringResource(R.string.game_guide),
            stringResource(R.string.game_btn),
        ) {
            navController.navigate(GameNav.route)
        }
        Spacer(modifier = Modifier.size(12.dp))
        if (completedTimeOX == 0L) {
            LargeSquareCardWithAnimation(
                R.raw.animation_ox_quiz_card,
                stringResource(R.string.ox_quiz_guide)
            ) {
                navController.navigate(QuizNav.route)
            }
        } else {
            LargeSquareCardWithAnimation(
                R.raw.animation_four_quiz_card,
                stringResource(R.string.four_quiz_guide)
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

            PrimaryButton(buttonText, Modifier) {
                onClick()
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