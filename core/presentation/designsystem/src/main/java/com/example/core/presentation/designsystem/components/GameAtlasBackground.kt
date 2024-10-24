package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.example.core.presentation.designsystem.GameAtlasTheme

@Composable
fun GameAtlasBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    imageBackground: String = "",
    content: @Composable ColumnScope.() -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (imageBackground.isNotEmpty()) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.1f),
                model = imageBackground,
                contentDescription = imageBackground,
                contentScale = ContentScale.Crop,
                error = {
                    PlaceholderImage()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (hasToolbar) {
                        Modifier
                    } else {
                        Modifier.systemBarsPadding()
                    }
                )
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun GradientBackgroundPreview() {
    GameAtlasTheme {
        GameAtlasBackground(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}