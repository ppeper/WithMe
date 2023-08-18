package com.bonobono.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        GifLoader(modifier = modifier.align(Alignment.Center)
            .size(100.dp), source = R.raw.loading_gif)
    }
}

@Preview
@Composable
fun PreviewLoading() {
    LoadingView()
}