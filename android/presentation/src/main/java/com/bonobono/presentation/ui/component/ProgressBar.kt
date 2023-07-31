package com.bonobono.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue

@Composable
fun ProgressBar(icon: ImageVector, title: String, percent: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Column {
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "아이콘",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(LightGray)
                        .padding(12.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Column {
                    Text(
                        text = "구조율",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Black_100
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "13%",
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
                color = PrimaryBlue,
                trackColor = LightGray,
                progress = 0.5f
            )
        }
    }
}

@Preview
@Composable
fun PreviewProgressBar() {
    ProgressBar(Icons.Filled.Star, "2", 0.1)
}
