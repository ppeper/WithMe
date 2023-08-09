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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CheckCountDialog
import com.bonobono.presentation.ui.main.component.PromptOXButtonRow
import com.bonobono.presentation.ui.main.component.QuizPromptBox
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.main.component.OverDialog
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.viewmodel.MissionViewModel

private const val TAG = "QuizScreen"

@Composable
fun QuizScreen(type: String, navController: NavController, missionViewModel: MissionViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        missionViewModel.getMission(1, type)
    }

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
        QuizPromptBox(modifier = Modifier.align(Alignment.BottomCenter), navController = navController)
    }
}

@Composable
fun QuizPromptBox(modifier: Modifier, navController: NavController, missionViewModel: MissionViewModel = hiltViewModel()) {
    val missionResult by missionViewModel.mission.collectAsState()
    val problemState = remember { mutableStateOf(Mission()) }
    val problemTextState = remember { mutableStateOf("") }

    val isSuccessResult by missionViewModel.isSuccess.collectAsState()
    val isSuccessState = remember { mutableStateOf(false) }

    val showDialog = remember { mutableStateOf(false) }

    getProblem(missionResult, problemState, problemTextState)

    checkAnswer(isSuccessState = isSuccessResult, isSuccess = isSuccessState, showDialog = showDialog)

    if(showDialog.value) {
        if(isSuccessState.value) {
            OverDialog(title = "정답!!", content = "+Exp 10", source = R.raw.animation_fairy) {
                missionViewModel.putCompletedTime(Constants.OX_QUIZ, System.currentTimeMillis())
                navController.popBackStack()
            }
        } else {
            OverDialog(title = "실패..", content = "", source = R.raw.animation_devil) {
                missionViewModel.putCompletedTime(Constants.OX_QUIZ, System.currentTimeMillis())
                navController.popBackStack()
            }
        }
    }

    QuizPromptBox(
        name = stringResource(id = R.string.fairy_name),
        content = stringResource(R.string.quiz_prompt_guide),
        problem = problemTextState.value,
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
                        problemState.value.problemId
                    )
                )
                
                missionViewModel.putCompletedTime(
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
                        problemState.value.problemId
                    )
                )
                missionViewModel.putCompletedTime(
                    Constants.OX_QUIZ,
                    System.currentTimeMillis()
                )
            }
        )
    }
}

fun getProblem(
    missionResult: NetworkResult<Mission>,
    problemState: MutableState<Mission>,
    problemTextState: MutableState<String>
) {
    when (missionResult) {
        is NetworkResult.Loading -> {}
        is NetworkResult.Success -> {
            problemState.value = missionResult.data
            problemTextState.value = problemState.value.problem
        }

        is NetworkResult.Error -> {}
    }
}


fun checkAnswer(
    isSuccessState: NetworkResult<Boolean>,
    isSuccess: MutableState<Boolean>,
    showDialog: MutableState<Boolean>
) {
    when (isSuccessState) {
        is NetworkResult.Loading -> {
            Log.d(TAG, "checkAnswer: loading")}
        is NetworkResult.Success -> {
            isSuccess.value = isSuccessState.data
            showDialog.value = true
            Log.d(TAG, "checkAnswer: ${showDialog.value}")
        }
        is NetworkResult.Error -> {
            Log.d(TAG, "checkAnswer: error")
        }
    }
}
