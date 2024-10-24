package com.example.feature.genres.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.database.PreviewData
import com.example.core.domain.genre.Genre
import com.example.core.presentation.designsystem.ControllerIcon
import com.example.core.presentation.designsystem.GameAtlasTheme
import com.example.core.presentation.designsystem.Purple80
import com.example.core.presentation.designsystem.components.Chip
import com.example.core.presentation.designsystem.components.ImageCard
import com.example.core.presentation.designsystem.roundedCornerShape
import com.example.feature.genres.R

@Composable
fun GenreCard(
    modifier: Modifier = Modifier,
    genreItem: Genre,
    borderShape: Shape = roundedCornerShape.small,
    aspectRatio: Float = 1.4f
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = if (genreItem.isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                shape = borderShape,
            ),
        shape = borderShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(modifier = modifier.aspectRatio(aspectRatio)) {
            ImageCard(
                modifier = modifier,
                imageUrl = genreItem.imageBackground,
                contentDescription = stringResource(R.string.genre_image),
                fullOpacity = genreItem.isSelected,
                aspectRatio = aspectRatio
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background.copy(alpha = 0.3f)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = genreItem.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .height(12.dp)
                    .alpha(if (genreItem.isSelected) 1f else 0.5f),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    imageVector = if (genreItem.isSelected) Icons.Default.CheckCircle else Icons.Default.AddCircle,
                    contentDescription = stringResource(R.string.check_circle),
                    tint = if (genreItem.isSelected) MaterialTheme.colorScheme.secondary else Purple80
                )
            }
            Chip(modifier = Modifier
                .padding(start = 12.dp, top = 12.dp),
                text = genreItem.gameCount.toString(),
                icon = ControllerIcon,
                hasBackground = true,
                backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                textColor = MaterialTheme.colorScheme.primary,
                iconColor = MaterialTheme.colorScheme.primary,
                textStyle = MaterialTheme.typography.bodySmall,
                )
        }

    }
}

@Preview
@Composable
private fun GameCardPreview() {
    GameAtlasTheme {
        GenreCard(genreItem = PreviewData.getGenreData().first())
    }
}