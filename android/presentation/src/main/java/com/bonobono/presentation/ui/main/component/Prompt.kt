package com.bonobono.presentation.ui.main.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.domain.model.mission.Choice
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.SubmitButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.common.LottieLoader
import com.bonobono.presentation.ui.theme.Green
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.White


@Composable
fun QuizPromptBox(
    name: String,
    content: String,
    problem: String,
    modifier: Modifier,
    choices: List<Choice> = listOf(),
    selectedIndex: MutableState<Int> = mutableStateOf(0),
    bottomRow: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(12.dp),
            style = CustomTextStyle.quizTitleStyle,
            textAlign = TextAlign.Center
        )
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 12.dp))
        Text(
            text = content,
            modifier = Modifier.padding(12.dp),
            style = CustomTextStyle.quizContentStyle
        )

        Text(
            text = problem,
            modifier = Modifier.padding(12.dp),
            style = CustomTextStyle.quizContentStyle
        )

        Column {
            choices.forEachIndexed { index, choice ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedIndex.value = index
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = index == selectedIndex.value,
                        onClick = { selectedIndex.value = index }
                    )
                    Text(text = choice.content, style = CustomTextStyle.quizContentStyle)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            bottomRow()
        }
    }
}

@Composable
fun GamePromptBox(name: String, content: String, modifier: Modifier) {
    Card(
        modifier = modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(White), verticalAlignment = Alignment.CenterVertically) {
            GifLoader(modifier = Modifier.size(100.dp), source = R.raw.animation_fairy)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = name,
                    modifier = Modifier.padding(12.dp),
                    style = CustomTextStyle.quizContentStyle.copy(fontSize = 18.sp)
                )
                Divider(thickness = 1.dp, modifier = Modifier.padding(4.dp))
                Text(
                    text = content,
                    modifier = Modifier.padding(12.dp),
                    style = CustomTextStyle.quizContentStyle.copy(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun ARPromptBox(
    name: String,
    content: String,
    modifier: Modifier,
    bottomRow: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                White,
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
    ) {
        Text(text = name, style = CustomTextStyle.quizTitleStyle)
        Divider(thickness = 1.dp, modifier = Modifier.padding(4.dp))
        Text(text = content, style = CustomTextStyle.quizContentStyle.copy(fontSize = 18.sp))
        Spacer(modifier = Modifier.size(64.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            bottomRow()
        }
    }
}

@Composable
fun PromptTwoButtonRow(
    modifier: Modifier,
    leftButton: @Composable () -> Unit,
    rightButton: @Composable () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        leftButton()
        Spacer(modifier = Modifier.size(4.dp))
        rightButton()
    }
}

@Composable
fun PromptOXButtonRow(
    modifier: Modifier,
    textStyle: TextStyle = CustomTextStyle.quizContentStyle,
    onClickX: () -> Unit,
    onClickO: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.weight(0.3f),
            onClick = onClickX,
            colors = ButtonDefaults.buttonColors(
                containerColor = Red,
                contentColor = White,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "X", style = textStyle.copy(color = White))
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Button(
            modifier = Modifier.weight(0.3f),
            onClick = onClickO,
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "O", style = textStyle.copy(color = White))
        }
    }
}

@Composable
fun PromptInputRow(
    modifier: Modifier,
    value: String,
    hint: String = "정답을 입력하세요..",
    onValueChange: (String) -> Unit,
    submitButton: String,
    onClick: () -> Unit = {},
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp)
                .weight(0.7f),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    style = CustomTextStyle.quizContentStyle.copy(color = LightGray)
                )
                Text(text = hint, style = CustomTextStyle.quizContentStyle.copy(color = LightGray))
            },
            textStyle = CustomTextStyle.quizContentStyle
        )

        Spacer(modifier = Modifier.size(4.dp))
        SubmitButton(
            modifier = Modifier.weight(0.3f),
            text = submitButton,
            CustomTextStyle.quizContentStyle
        ) {
            onClick()
        }
    }
}

@Composable
fun CountdownTimer(modifier: Modifier = Modifier, timeLeft: Int) {

    Card(
        modifier = modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            LottieLoader(source = R.raw.animation_timer, modifier = Modifier.size(64.dp))
            Text(
                text = timeLeft.toString(),
                style = CustomTextStyle.quizContentStyle
            )
        }
    }
}
