package com.bonobono.presentation.ui.main.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.Green
import com.bonobono.presentation.ui.theme.LightGray

@Composable
fun LinearProgressBar(source: Int, title: String, percent: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Column {
            Row {
                Icon(
                    painter = painterResource(id = source),
                    contentDescription = "아이콘",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(LightGray)
                        .padding(12.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Black_100
                    )
                    Text(
                        text = "$percent%",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Black_100
                    )
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = Green,
                trackColor = LightGray,
                progress = percent*0.01f
            )
        }
    }
}

@Composable
fun CircularProgressBar(percent: Float, content: String) {
    val animatedProgress = animateFloatAsState(targetValue = percent, label = "")

    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(80.dp)) {
            drawArc(
                color = LightGray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 8.dp.toPx())
            )
            drawArc(
                color = Green,
                startAngle = -90f,
                sweepAngle = animatedProgress.value * 360f,
                useCenter = false,
                style = Stroke(width = 8.dp.toPx())
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${(animatedProgress.value * 100).toInt()}%",
                style = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = content,
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}
