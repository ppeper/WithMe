package com.bonobono.presentation.ui.map

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bonobono.presentation.ui.common.CancelButton
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.game.component.ARTextBox
import com.google.ar.core.Config
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
    const val chapterFourContent = "저주를 풀어줬구나!! 믿고있었어\n고마워 우린 이제 앞으로 영원한 친구야!!\n\n친구가 된 기념으로 내 이름을 지어줄래?"
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
    val titleList = listOf<String>(PromptString.chapterOneTitle, PromptString.chapterTwoTitle, PromptString.chapterThreeTitle, PromptString.chapterFourTitle)
    val contentList = listOf<String>(PromptString.chapterOneContent, PromptString.chapterTwoContent, PromptString.chapterThreeContent, PromptString.chapterFourContent)

    var name by remember { mutableStateOf(titleList[0]) }
    var content by remember { mutableStateOf(contentList[0]) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val currentModel = remember {
                mutableStateOf("egg")
            }
            ARScreen(currentModel.value)
//            ARTextBox(
//                name = name,
//                content = content,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(alignment = Alignment.BottomCenter),
//                // Row 넣기
//             )
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
                    onAnchorChanged = {
                        placeModelButton.value = !isAnchored
                    }
                    onHitResult = { node, hitResult ->
                        placeModelButton.value = node.isTracking
                    }

                }
                nodes.add(modelNode.value!!)
            },
            onSessionCreate = {
                planeRenderer.isVisible = false
            }
        )
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
fun AnimationScreen() {

}






