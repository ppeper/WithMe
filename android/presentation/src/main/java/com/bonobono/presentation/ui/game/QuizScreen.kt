package com.bonobono.presentation.ui.game

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.game.component.PromptOXButtonRow
import com.bonobono.presentation.ui.game.component.QuizPromptBox
import com.bonobono.presentation.ui.main.component.GifLoader
import com.bonobono.presentation.viewmodel.MissionViewModel
import kotlin.math.log

private const val TAG = "QuizScreen"

@Composable
fun QuizScreen(quizViewModel: MissionViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        quizViewModel.getOXQuiz(1)
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
                .align(Alignment.TopCenter), source = R.raw.animation_fairy
        )
        OXQuizPromptBox(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun OXQuizPromptBox(modifier: Modifier, viewModel: MissionViewModel = hiltViewModel()) {
    val mission by remember { mutableStateOf(Mission()) }
    var problemText by remember { mutableStateOf("") }
    val isSuccessState by viewModel.isSuccessOXQuizState.collectAsStateWithLifecycle()
    var isSuccess = remember { mutableStateOf(false) }

    QuizPromptBox(
        name = "클로버 요정",
        content = "오늘도 지구를 지켜줘서 고마워!\n\n내가 내는 문제를 맞추면 동물 친구들을 성장 시킬 수 있어!\n오늘도 화이팅!",
        problem = problemText,
        modifier = modifier
    ) {
        PromptOXButtonRow(
            modifier = Modifier.padding(12.dp),
            onClickX = {
                checkOXAnswer(isSuccessState = isSuccessState, isSuccess = isSuccess)
                viewModel.postIsSuccessOXQuiz(isSuccess = IsSuccess("X", 1, mission.problemId))
            },
            onClickO = {
                checkOXAnswer(isSuccessState = isSuccessState, isSuccess = isSuccess)
                viewModel.postIsSuccessOXQuiz(isSuccess = IsSuccess("O", 1, mission.problemId))
            }
        )
    }

    val problemState by viewModel.oxQuizState.collectAsStateWithLifecycle()
    when (problemState) {
        is NetworkResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is NetworkResult.Success -> {
            val oxQuiz = (problemState as NetworkResult.Success<Mission>).data
            problemText = oxQuiz.problem
        }

        is NetworkResult.Error -> {
            Log.d("quiz", "QuizScreen: ${(problemState as NetworkResult.Error).message}")
        }
    }
}


fun checkOXAnswer(
    isSuccessState: NetworkResult<Boolean>,
    isSuccess: MutableState<Boolean>,
) {
    when (isSuccessState) {
        is NetworkResult.Loading -> {
            Log.d(TAG, "checkOXAnswer: ")
        }

        is NetworkResult.Success -> {
            isSuccess.value = isSuccessState.data
            Log.d(TAG, "checkOXAnswer: ${isSuccess.value}")
        }

        is NetworkResult.Error -> {
            Log.d(TAG, "checkOXAnswer: error")
        }
    }
}