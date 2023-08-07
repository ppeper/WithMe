package com.bonobono.presentation.ui.map

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.CancelButton
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.game.component.ARTextBox
import com.bonobono.presentation.ui.game.component.PromptInputRow
import com.bonobono.presentation.ui.game.component.PromptOXButtonRow
import com.bonobono.presentation.ui.game.component.PromptTwoButtonRow
import com.bonobono.presentation.ui.main.component.GifLoader
import com.google.ar.core.Config
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCameraState
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.PlacementMode

object PromptString {
    const val chapterOneTitle = "???"
    const val chapterTwoTitle = "???"
    const val chapterThreeTitle = "쓰레기 마왕"
    const val chapterFourTitle = "바다 고래"

    const val chapterOneContent = "쓰레기 마왕이 나한테 저주를 걸었어!\n문제를 풀어서 나한테 걸린 저주를 풀고 날 구해줘!"
    const val chapterTwoContent = "내 부탁을 들어줘서 고마워! 힌트를 줄게!"
    const val chapterThreeContent = "저주를 풀러 오다니 어리석구나!\n어디 해볼테면 해봐라!"
    const val chapterFourContent =
        "저주를 풀어줬구나!! 믿고있었어\n고마워 우린 이제 앞으로 영원한 친구야!!\n\n친구가 된 기념으로 내 이름을 지어줄래?"
}

data class Prompt(
    var title: String,
    var content: String,
    var model: String,
    var animation: Int,
    var leftButtonContent: String,
    var rightButtonContent: String
)


@Composable
fun CameraScreen() {
    val promptsList = listOf(
        Prompt(PromptString.chapterOneTitle, PromptString.chapterOneContent, "", 0, "거절", "수락"),
        Prompt(PromptString.chapterTwoTitle, PromptString.chapterTwoContent, "", 0, "뒤로", "다음"),
        Prompt(PromptString.chapterThreeTitle, PromptString.chapterThreeContent, "", 0, "X", "O"),
        Prompt(PromptString.chapterFourTitle, PromptString.chapterFourContent, "", 0, "이름..", "확인"),
    )

    var chapter by remember { mutableStateOf(0) }
    var prompt by remember { mutableStateOf(promptsList[0]) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val currentModel = remember {
                mutableStateOf("egg")
            }
            if(chapter >= 2) {
                AnimationScreen(modifier = Modifier.fillMaxSize())
            } else {
                ARScreen(currentModel.value)
            }
            ARTextBox(
                name = prompt.title,
                content = prompt.content,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter),
                // Row 넣기
            ) {
                if (chapter == 3) {
                    PromptInputRow(
                        modifier = Modifier.padding(4.dp),
                        value = "이름을 입력해주세요.",
                        onValueChange = {},
                        prompt.rightButtonContent
                    )
                } else if (chapter == 2) {
                    PromptOXButtonRow(modifier = Modifier, onClickX = {}, onClickO = {})
                } else {
                    PromptTwoButtonRow(
                        modifier = Modifier.align(alignment = Alignment.BottomEnd),
                        leftButton = {
                            CancelButton(
                                modifier = Modifier,
                                text = prompt.leftButtonContent,
                                textStyle = CustomTextStyle.quizContentStyle
                            ) {

                            }
                        }, rightButton = {
                            SubmitButton(
                                modifier = Modifier,
                                text = prompt.rightButtonContent,
                                textStyle = CustomTextStyle.quizContentStyle
                            ) {
                                chapter = (chapter + 1) % 4
                                prompt = promptsList[chapter]
                            }
                        })
                }
            }
        }

    }
}



@Composable
fun ARScreen(model: String) {
    val nodes = remember {
        mutableListOf<ArNode>()
    }
    val modelNode = remember {
        mutableStateOf<ArModelNode?>(null)
    }
    val placeModelButton = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        ARScene(
            modifier = Modifier.fillMaxSize(),
            nodes = nodes,
            planeRenderer = true,
            onCreate = { arSceneView ->
                arSceneView.lightEstimationMode = Config.LightEstimationMode.DISABLED
                arSceneView.planeRenderer.isShadowReceiver = false
                modelNode.value = ArModelNode(arSceneView.engine, PlacementMode.INSTANT).apply {
                    loadModelGlbAsync(
                        glbFileLocation = "models/${model}.glb",
                        scaleToUnits = 0.8f
                    ) {

                    }
                }
                nodes.add(modelNode.value!!)
            },
            onSessionCreate = {
                planeRenderer.isVisible = false
            }
        ) {

        }
    }


    LaunchedEffect(key1 = model) {
        modelNode.value?.loadModelGlbAsync(
            glbFileLocation = "models/${model}.glb",
            scaleToUnits = 0.8f
        )
        Log.e("errorloading", "ERROR LOADING MODEL")
    }
}

// gif 파일
@Composable
fun AnimationScreen(modifier: Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        val cameraState = rememberCameraState()
        var camSelector by remember { mutableStateOf(CamSelector.Back) }
        CameraPreview(
            modifier = modifier,
            cameraState = cameraState,
            camSelector = camSelector,
        ) {
            GifLoader(modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .align(Alignment.TopCenter), source = R.raw.animation_devil)
        }
    }
}






