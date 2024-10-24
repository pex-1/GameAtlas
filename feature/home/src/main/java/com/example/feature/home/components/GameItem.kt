package com.example.feature.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.database.PreviewData
import com.example.core.domain.game.Game
import com.example.core.domain.util.simpleDateFormat
import com.example.core.presentation.designsystem.DarkPurple10
import com.example.core.presentation.designsystem.DateIcon
import com.example.core.presentation.designsystem.GameAtlasTheme
import com.example.core.presentation.designsystem.Neutral40
import com.example.core.presentation.designsystem.Neutral50
import com.example.core.presentation.designsystem.StarIcon
import com.example.core.presentation.designsystem.components.Chip
import com.example.core.presentation.designsystem.components.ChipGroup
import com.example.core.presentation.designsystem.components.Gap
import com.example.core.presentation.designsystem.components.util.Constants
import com.example.feature.home.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    game: Game,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onClick: (Game) -> Unit
) {
    with(sharedTransitionScope) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(DarkPurple10)
                .clickable {
                    onClick(game)
                }
                .padding(horizontal = 16.dp)
        ) {
            GameImageCard(
                imageUrl = game.backgroundImage,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(100.dp)
                    .width(85.dp),
                animatedVisibilityScope = animatedVisibilityScope,
                aspectRatio = 85 / 100f
            )

            Gap(size = 16.dp)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Gap(size = 8.dp)
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = game.name),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = Constants.DETAIL_TEXT_DELAY)
                        }
                    ),
                    text = game.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Gap(size = 8.dp)
                Chip(text = "${game.rating}/5",
                    textStyle = MaterialTheme.typography.labelSmall,
                    textColor = Neutral40,
                    icon = StarIcon,
                    iconColor = MaterialTheme.colorScheme.secondary,
                    contentDescription = stringResource(R.string.star_icon)
                    )

                Gap(size = 8.dp)
                game.genres.map { it.name }.let { ChipGroup(tag = it) }
                Gap(size = 8.dp)
                game.released?.let { date ->
                    Chip(text = date.simpleDateFormat(),
                        textStyle = MaterialTheme.typography.labelMedium,
                        iconColor = Neutral50,
                        textColor = Neutral50,
                        icon = DateIcon,
                        contentDescription = stringResource(id = R.string.date_icon)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun GameItemPreview() {
    GameAtlasTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                GameItem(
                    game = PreviewData.getGameData(4, 3),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                ) {}
            }
        }
    }
}