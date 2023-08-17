package com.bonobono.presentation.ui.main.mission

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.MiniGame
import com.bonobono.presentation.R
import com.bonobono.presentation.model.Trash
import com.bonobono.presentation.ui.common.CancelButton
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.CountdownTimer
import com.bonobono.presentation.ui.main.component.DragTarget
import com.bonobono.presentation.ui.main.component.DropTarget
import com.bonobono.presentation.ui.main.component.GamePromptBox
import com.bonobono.presentation.ui.main.component.LongPressDraggable
import com.bonobono.presentation.ui.main.component.PromptTwoButtonRow
import com.bonobono.presentation.ui.main.component.QuizPromptBox
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.common.LottieLoader
import com.bonobono.presentation.ui.main.component.OverDialog
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.viewmodel.MissionViewModel
import kotlinx.coroutines.delay

data class TrashCan(
    val name: String,
    val image: Int
)

private const val TAG = "GameScreen"

@Composable
fun GameScreen(
    navController: NavHostController,
    missionViewModel: MissionViewModel = hiltViewModel()
) {
    val trash = Trash("계란", R.drawable.image_egg)
    var timeLeft by remember { mutableStateOf(20) }

    val miniGame by missionViewModel.miniGame.collectAsStateWithLifecycle()
    val isSuccess by missionViewModel.isSuccess.collectAsState()

    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }


    if(timeLeft == 0) {
        missionViewModel.postIsSuccess(
            isSuccess = IsSuccess("", 1, miniGame.problemId),
            Constants.GAME
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LottieLoader(source = R.raw.animation_game_background, modifier = Modifier.fillMaxSize())
        LongPressDraggable(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter)
            ) {
                GamePromptBox(
                    name = stringResource(R.string.fairy_name),
                    content = stringResource(R.string.game_prompt_guide),
                    modifier = Modifier
                )
                CountdownTimer(modifier = Modifier.align(alignment = Alignment.End), timeLeft)
            }
            TrashItemCard(
                trashItem = trash,
                Modifier.align(alignment = Alignment.Center),
                miniGame,
                missionViewModel
            )
            TrashCanScreen(
                Modifier.align(alignment = Alignment.BottomCenter),
                miniGame,
                missionViewModel
            )
        }
    }

    LaunchedEffect(Unit) {
        missionViewModel.getMission(1, Constants.GAME)
    }

    if (isSuccess == true) {
        timeLeft = -1
        OverDialog(title = "정답!!", content = "+Exp 5", source = R.raw.animation_fairy) {
            missionViewModel.putLong(Constants.GAME, System.currentTimeMillis())
            navController.popBackStack()
        }
    } else if (isSuccess == false) {
        timeLeft = -1
        OverDialog(title = "실패..", content = "", source = R.raw.animation_devil) {
            missionViewModel.putLong(Constants.GAME, System.currentTimeMillis())
            navController.popBackStack()
        }
    }
}

@Composable
fun GameOverPrompt(modifier: Modifier, navController: NavHostController) {
    GifLoader(modifier = Modifier.fillMaxSize(), source = R.raw.animation_devil)
    QuizPromptBox(
        name = stringResource(R.string.evil_name),
        content = stringResource(R.string.game_over),
        problem = "",
        modifier = modifier
    ) {
        PromptTwoButtonRow(modifier = Modifier.padding(12.dp),
            leftButton = {
                CancelButton(
                    modifier = Modifier,
                    text = stringResource(R.string.exit),
                    textStyle = CustomTextStyle.quizContentStyle.copy(color = White)
                ) {
                    navController.popBackStack()
                }
            },
            rightButton = {
                SubmitButton(
                    modifier = Modifier,
                    text = stringResource(R.string.redo),
                    textStyle = CustomTextStyle.quizContentStyle.copy(color = White)
                ) {

                }
            })
    }
}

@Composable
fun TrashItemCard(
    trashItem: Trash,
    modifier: Modifier,
    mission: MiniGame,
    missionViewModel: MissionViewModel
) {

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            DragTarget(modifier = Modifier.size(130.dp), dataToDrop = trashItem) {
                if(getTrashIcon(mission.problemId) != -1) {
                    Image(
                        painter = painterResource(id = getTrashIcon(mission.problemId)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "${mission.problem}",
                style = CustomTextStyle.mapTitleTextStyle
            )
        }
    }
}

@Composable
fun TrashCanScreen(modifier: Modifier, miniGame: MiniGame, missionViewModel: MissionViewModel) {
    val items = listOf<TrashCan>(
        TrashCan("일반쓰레기", R.drawable.trashcan),
        TrashCan("플라스틱", R.drawable.trashcan),
        TrashCan("유리", R.drawable.trashcan),
        TrashCan("캔", R.drawable.trashcan),
    )
    Row(
        modifier = modifier
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
            )
            .fillMaxHeight(0.3f)
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items.forEach {
            TrashCanCard(it, Modifier, missionViewModel, miniGame)
        }
    }
}

@Composable
fun TrashCanCard(
    trashCan: TrashCan,
    modifier: Modifier,
    missionViewModel: MissionViewModel,
    miniGame: MiniGame
) {
    DropTarget<Trash>(
        modifier = modifier
    ) { isInBound, foodItem ->
        val bgColor = if (isInBound) {
            PrimaryBlue
        } else {
            White
        }
        if (isInBound) {
            missionViewModel.postIsSuccess(
                isSuccess = IsSuccess(trashCan.name, 1, miniGame.problemId),
                Constants.GAME
            )
        }
        Column(
            modifier = Modifier
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                .fillMaxHeight(0.8f)
                .background(
                    bgColor,
                    RoundedCornerShape(16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = trashCan.image), contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = trashCan.name,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

fun getTrashIcon(id: Int): Int {
    return when (id) {
        1 -> R.drawable.image_egg
        2 -> R.drawable.image_bone
        3 -> R.drawable.image_bottle
        4 -> R.drawable.image_pot
        5-> R.drawable.image_clean
        6 -> R.drawable.image_paper
        7 -> R.drawable.image_dojagi
        else -> -1
    }
}




