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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonobono.domain.model.character.UserCharacter
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
import com.bonobono.presentation.ui.common.topbar.screen.MissionScreen
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.characterList
import com.bonobono.presentation.viewmodel.CharacterViewModel
import com.bonobono.presentation.viewmodel.MissionViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val TAG = "MissionScreen"

@Composable
fun MissionScreen(
    navController: NavHostController,
    missionViewModel: MissionViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel
) {
    val curMainCharacter by characterViewModel.character.collectAsState()
    LaunchedEffect(key1 = Unit) {
        MissionScreen.buttons
            .onEach { button ->
                when (button) {
                    MissionScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    missionViewModel.removeCompletedTime() // 하루 지나면 삭제
    val completedTimeOX = missionViewModel.getLong(Constants.OX_QUIZ)
    val completedTimeFour = missionViewModel.getLong(Constants.FOUR_QUIZ)
    val completedGame = missionViewModel.getLong(Constants.GAME)
    val completedTimeAttendance = missionViewModel.getLong(Constants.ATTENDANCE)

    val totalScore by missionViewModel.totalScore.collectAsState()


    var attendanceIsEnabled = remember { mutableStateOf(completedTimeAttendance == 0L) }
    var gameIsEnabled = remember { mutableStateOf(completedGame == 0L) }
    var quizIsEnabled = remember {
        mutableStateOf(completedTimeOX == 0L && completedTimeFour == 0L)
    }

    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        val mainId = missionViewModel.getLong(Constants.MAIN_CHARACTER)
        missionViewModel.getScore(1)
        characterViewModel.getUserCharacterList()
        characterViewModel.getMainCharacter()
    }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }) {
        it
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            UserInformationItem(totalScore, curMainCharacter)
            Spacer(modifier = Modifier.size(12.dp))
            DailyGameItem(
                R.raw.animation_check,
                stringResource(R.string.attendance_guide),
                stringResource(R.string.attendance_btn),
                attendanceIsEnabled
            ) {
                missionViewModel.postAttendance(1)
                missionViewModel.putLong(Constants.ATTENDANCE, System.currentTimeMillis())
                attendanceIsEnabled.value = false
                missionViewModel.postAttendance(1)
                coroutineScope.launch {
                    snackBarHostState.showSnackbar("출석체크 완료! +Exp 5")
                }
                characterViewModel.getUserCharacterList()
                characterViewModel.getMainCharacter()
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
            } else if (completedTimeFour == 0L) {
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
fun UserInformationItem(totalScore: TotalScore, curCharacter: UserCharacter) {
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
            val image = characterList.find { it.id == curCharacter.char_ord_id }?.icon ?: R.drawable.default_profile
            ProfilePhoto(
                profileImage = image, modifier = Modifier
                    .clip(CircleShape)
                    .background(White)
                    .border(BorderStroke(1.dp, LightGray), shape = CircleShape)
            )
            Row() {
                CircularProgressBar(percent = totalScore.attendanceScore * 0.01f, "출셕율")
                Spacer(modifier = Modifier.size(24.dp))
                CircularProgressBar(percent = totalScore.totalScore * 0.01f, "미션달성율")
            }
        }
        LinearProgressBar(
            source = R.drawable.ic_check,
            title = stringResource(R.string.exp_txt),
            percent = curCharacter.experience
        )
    }
}
