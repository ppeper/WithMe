package com.bonobono.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray

@Composable
fun CharacterBlind() {
    Box {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(1.dp, DarkGray), shape = CircleShape)
                .background(LightGray)
                .graphicsLayer {
                    alpha = 0.05f
                },
            painter = painterResource(id = R.drawable.beluga_whale),
            contentDescription = "캐릭터"
        )
    }
}

@Preview
@Composable
fun CharacterPreview() {
    CharacterBlind()
}

