package com.bonobono.presentation.ui.main.component

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
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray

@Composable
fun CharacterBlind(image: Int) {
    Box {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(1.dp, DarkGray), shape = CircleShape)
                .background(LightGray)
                .graphicsLayer {
                    alpha = 0.05f
                },
            painter = painterResource(id = image),
            contentDescription = "캐릭터"
        )
    }
}


