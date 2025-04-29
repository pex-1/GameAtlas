package com.example.feature.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.core.database.PreviewData
import com.example.core.domain.util.monthFormat
import com.example.core.presentation.designsystem.GameAtlasTheme
import com.example.core.presentation.designsystem.Neutral20
import com.example.core.presentation.designsystem.components.ChipGroup
import com.example.core.presentation.designsystem.components.GameAtlasBackground
import com.example.core.presentation.designsystem.components.GameAtlasScaffold
import com.example.core.presentation.designsystem.components.GameAtlasToolbar
import com.example.core.presentation.designsystem.components.ImageCard
import com.example.core.presentation.designsystem.components.PlaceholderImage
import com.example.core.presentation.designsystem.components.RatingBar
import com.example.core.presentation.designsystem.components.fadeInAnimation
import com.example.core.presentation.designsystem.components.offsetAnimation
import com.example.core.presentation.designsystem.components.util.Constants
import com.example.core.presentation.designsystem.roundedCornerShape
import com.example.feature.details.util.getPlatformImage
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreenRoot(
    gameId: Int,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: DetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    viewModel.getGameById(gameId)
    DetailsScreen(viewModel.state, animatedVisibilityScope, sharedTransitionScope) { action ->
        when (action) {
            DetailsAction.OnBackClick -> onBackClick()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
private fun DetailsScreen(
    state: DetailsState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onAction: (DetailsAction) -> Unit
) {

    GameAtlasScaffold(
        modifier = Modifier.fillMaxSize(),
        topAppBar = {
            GameAtlasToolbar(showBackButton = true,
                onBackClick = {
                    onAction(DetailsAction.OnBackClick)
                })
        }
    ) { values ->

        with(sharedTransitionScope) {
            state.game?.let { game ->
                val animationsEnabled = state.animationsEnabled
                GameAtlasBackground(imageBackground = state.game.backgroundImage) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                            .navigationBarsPadding()
                            .verticalScroll(rememberScrollState(), enabled = state.scrollEnabled)
                            .padding(values),
                    ) {
                        Box(
                            modifier = Modifier
                                .height(400.dp)
                                .fillMaxWidth(0.7f)
                                .clip(shape = roundedCornerShape.small)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .sharedElement(
                                        state = rememberSharedContentState(key = game.backgroundImage),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        boundsTransform = { _, _ ->
                                            tween(durationMillis = Constants.DETAILS_IMAGE_DELAY)
                                        }
                                    )
                                    .fillMaxWidth()
                                    .height(400.dp),
                                model = game.backgroundImage,
                                contentDescription = game.name,
                                contentScale = ContentScale.Crop,
                                error = {
                                    PlaceholderImage()
                                }
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .let {
                                        if (animationsEnabled)
                                            it.alpha(fadeInAnimation(animatedAlpha = state.fadeAnimation)) else it
                                    }
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                MaterialTheme.colorScheme.background
                                            ),
                                            startY = 200f
                                        )
                                    )
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .applyOffsetAndFadeInAnimation(
                                        animationsEnabled,
                                        state.offsetAnimation,
                                        state.fadeAnimation
                                    )
                                    .align(Alignment.BottomCenter)
                                    .padding(top = 10.dp, bottom = 12.dp)
                                    .height(28.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                game.released?.let { date ->
                                    Text(
                                        text = date.monthFormat(),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.background,
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                            .clip(shape = roundedCornerShape.extraSmall)
                                            .background(Neutral20)
                                            .padding(vertical = 4.dp, horizontal = 4.dp)
                                    )
                                }

                                game.parentPlatforms.map { it.platformType }.toSet()
                                    .forEach { platformType ->

                                        val image = platformType.getPlatformImage()
                                        if (image != null) {
                                            Icon(
                                                imageVector = image,
                                                tint = Neutral20,
                                                contentDescription = platformType.name,
                                                modifier = Modifier
                                                    .padding(horizontal = 4.dp, vertical = 4.dp)
                                                    .height(18.dp)
                                                    .width(18.dp)
                                            )
                                        }

                                    }

                            }
                        }
                        Text(
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(key = game.name),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = Constants.DETAIL_TEXT_DELAY)
                                    }
                                )
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            text = game.name,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .applyOffsetAndFadeInAnimation(
                                    animationsEnabled,
                                    state.offsetAnimation,
                                    state.fadeAnimation
                                )
                                .padding(vertical = 24.dp)
                                .height(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            RatingBar(rating = game.rating)
                        }

                        ChipGroup(
                            modifier = Modifier.applyOffsetAndFadeInAnimation(
                                animationsEnabled,
                                state.offsetAnimation,
                                state.fadeAnimation
                            ),
                            tag = state.game.genres.map { it.name },
                            horizontalAlignment = Alignment.CenterHorizontally
                        )

                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .applyOffsetAndFadeInAnimation(animationsEnabled, state.offsetAnimation, state.fadeAnimation)
                                .width(IntrinsicSize.Max),
                            text = state.game.description,
                            color = Neutral20,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify
                        )

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .applyOffsetAndFadeInAnimation(
                                    animationsEnabled,
                                    state.offsetAnimation,
                                    state.fadeAnimation
                                )
                                .padding(top = 24.dp),
                            state = rememberLazyListState(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            content = {
                                items(
                                    items = game.screenshots,
                                    key = { it.id }
                                ) {
                                    ImageCard(
                                        modifier = Modifier
                                            .height(300.dp)
                                            .width(300.dp),
                                        imageUrl = it.imageUrl,
                                        contentDescription = stringResource(R.string.game_screenshot),
                                        cornerShape = roundedCornerShape.extraSmall
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun Modifier.applyOffsetAndFadeInAnimation(
    animationsEnabled: Boolean,
    offsetAnimation: Animatable<Float, AnimationVector1D>,
    fadeAnimation: Animatable<Float, AnimationVector1D> ): Modifier {
    return this.let {
        if (animationsEnabled) {
            it
                .offset(y = offsetAnimation(animatedOffset = offsetAnimation))
                .alpha(fadeInAnimation(animatedAlpha = fadeAnimation))
        } else it
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(apiLevel = 34)
@Composable
private fun DetailsPreviewScreen() {
    GameAtlasTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                DetailsScreen(
                    DetailsState(
                        game = PreviewData.getGameData(9, 5),
                        description = PreviewData.getGameDescription(),
                    ),
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                ) {}
            }

        }

    }
}