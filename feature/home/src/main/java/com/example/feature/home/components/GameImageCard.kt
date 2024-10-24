package com.example.feature.home.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.core.presentation.designsystem.components.PlaceholderImage
import com.example.core.presentation.designsystem.components.ProgressIndicator
import com.example.core.presentation.designsystem.components.util.Constants
import com.example.core.presentation.designsystem.roundedCornerShape
import com.example.feature.home.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    contentDescription: String = stringResource(R.string.game_image),
    cornerShape: CornerBasedShape = roundedCornerShape.small,
    aspectRatio: Float = 1f
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(key = imageUrl),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = Constants.DETAILS_IMAGE_DELAY)
                }
            )
            .clip(cornerShape)
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
