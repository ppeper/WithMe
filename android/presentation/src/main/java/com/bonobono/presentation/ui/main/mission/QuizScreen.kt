package com.bonobono.presentation.ui.main.mission

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.main.component.PromptOXButtonRow
import com.bonobono.presentation.ui.main.component.QuizPromptBox
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.OverDialog
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.viewmodel.MissionViewModel

private const val TAG = "QuizScreen"

@Composable
fun QuizScreen(
    type: String,
    navController: NavController,
    missionViewModel: MissionViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_quiz),
            contentDescription = "배경",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        GifLoader(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .align(Alignment.TopCenter),
            source = R.raw.animation_fairy
        )
        QuizPromptBox(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            type
        )
    }
}

@Composable
fun QuizPromptBox(
    modifier: Modifier,
    navController: NavController,
    type: String,
    missionViewModel: MissionViewModel = hiltViewModel()
) {
    val mission by missionViewModel.mission.collectAsStateWithLifecycle()
    val isSuccess by missionViewModel.isSuccess.collectAsState()

    LaunchedEffect(Unit) {
        missionViewModel.getMission(1, type)
        Log.d(TAG, "QuizPromptBox: $mission")
    }


    checkAnswer(isSuccess = isSuccess, mission = mission, navController = navController, missionViewModel = missionViewModel)

    if (mission.choices.isNullOrEmpty()) {
        QuizPromptBox(
            name = stringResource(id = R.string.fairy_name),
            content = stringResource(R.string.quiz_prompt_guide),
            problem = mission.problem,
            modifier = modifier
        ) {
            PromptOXButtonRow(
                modifier = Modifier.padding(12.dp),
                onClickX = {
                    missionViewModel.postIsSuccess(
                        type = Constants.OX_QUIZ,
                        isSuccess = IsSuccess(
                            "X",
                            1,
                            mission.problemId
                        )
                    )

                    missionViewModel.putLong(
                        Constants.OX_QUIZ,
                        System.currentTimeMillis()
                    )
                },
                onClickO = {
                    missionViewModel.postIsSuccess(
                        type = Constants.OX_QUIZ,
                        isSuccess = IsSuccess(
                            "O",
                            1,
                            mission.problemId
                        )
                    )
                    missionViewModel.putLong(
                        Constants.OX_QUIZ,
                        System.currentTimeMillis()
                    )
                }
            )
        }
    } else {
        val inputValue = remember {
            mutableStateOf("")
        }
        val selectedIndex = remember {
            mutableStateOf(0)
        }
        QuizPromptBox(
            name = stringResource(id = R.string.fairy_name),
            content = stringResource(R.string.quiz_prompt_guide),
            problem = mission.problem,
            modifier = modifier,
            choices = mission.choices!!,
            selectedIndex = selectedIndex
        ) {
            SubmitButton(
                modifier = Modifier.padding(12.dp),
                text = "제출",
                textStyle = CustomTextStyle.quizContentStyle
            ) {

                missionViewModel.postIsSuccess(isSuccess = IsSuccess(
                    mission.choices!![selectedIndex.value].content,
                    1,
                    mission.problemId
                ), type = Constants.FOUR_QUIZ)

                missionViewModel.putLong(
                    Constants.FOUR_QUIZ,
                    System.currentTimeMillis()
                )
            }
        }
    }
}

@Composable
fun checkAnswer(isSuccess: Boolean?, mission: Mission, navController: NavController, missionViewModel: MissionViewModel) {
    if (isSuccess == true) {
        OverDialog(title = "정답!!", content = "+Exp 5", source = R.raw.animation_fairy, commentary = mission.commentary) {
            missionViewModel.putLong(Constants.OX_QUIZ, System.currentTimeMillis())
            navController.popBackStack()
        }
    } else if (isSuccess == false) {
        OverDialog(title = "실패..", content = "", source = R.raw.animation_devil,  commentary = mission.commentary) {
            missionViewModel.putLong(Constants.OX_QUIZ, System.currentTimeMillis())
            navController.popBackStack()
        }
    }
}

