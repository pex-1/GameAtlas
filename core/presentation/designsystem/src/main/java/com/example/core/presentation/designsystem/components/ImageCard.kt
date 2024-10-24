package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.core.presentation.designsystem.roundedCornerShape

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    cornerShape: CornerBasedShape = roundedCornerShape.small,
    fullOpacity: Boolean = true,
    aspectRatio: Float = 1f
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .clip(cornerShape)
            .alpha(if (fullOpacity) 1f else 0.2f)
            .aspectRatio(aspectRatio),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),

        contentScale = ContentScale.Crop,
        contentDescription = contentDescription,
        loading = {
            ProgressIndicator()
        },
        error = {
            PlaceholderImage()
        }
    )
}
