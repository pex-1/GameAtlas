package com.example.feature.genres

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.database.PreviewData
import com.example.core.presentation.designsystem.GameAtlasTheme
import com.example.core.presentation.designsystem.PurpleGray30
import com.example.core.presentation.designsystem.components.GameAtlasActionButton
import com.example.core.presentation.designsystem.components.GameAtlasBackground
import com.example.core.presentation.designsystem.components.GameAtlasEmptyState
import com.example.core.presentation.designsystem.components.GameAtlasScaffold
import com.example.core.presentation.designsystem.components.GameAtlasToolbar
import com.example.core.presentation.designsystem.components.Gap
import com.example.core.presentation.designsystem.components.util.DevicePreviews
import com.example.core.presentation.designsystem.components.util.DropDownItem
import com.example.core.presentation.designsystem.components.util.WindowInfo
import com.example.core.presentation.designsystem.components.util.rememberWindowInfo
import com.example.core.presentation.designsystem.roundedCornerShape
import com.example.feature.genres.components.GenreCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun GenreScreenRoot(
    onContinueClick: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    viewModel: GenreViewModel = koinViewModel()
) {
    GenreScreen(
        state = viewModel.state,
        showToolbar = viewModel.state.onboardingCompleted,
        onAction = { action ->
            when (action) {
                GenreAction.OnContinueClick -> onContinueClick(viewModel.state.onboardingCompleted)
                GenreAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenreScreen(
    state: GenreState,
    onAction: (GenreAction) -> Unit,
    showToolbar: Boolean = false,
    menuItems: List<DropDownItem> = emptyList(),
    borderShape: Shape = roundedCornerShape.medium
) {

    GameAtlasScaffold(
        modifier = Modifier.fillMaxSize(),
        topAppBar = {
            if (showToolbar) {
                GameAtlasToolbar(
                    showBackButton = true,
                    menuItems = menuItems,
                    onBackClick = {
                        onAction(GenreAction.OnBackClick)
                    }
                )
            }
        }
    ) { values ->
        GameAtlasBackground {

            val windowInfo = rememberWindowInfo()
            val isCompact = windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact

            if (state.showEmptyState) {
                GameAtlasEmptyState(isLoading = state.isLoading) {
                    onAction(GenreAction.OnRetryClick)
                }
            } else if (state.genres.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (isCompact) {
                                Modifier.padding(values)
                            } else {
                                Modifier
                                    .systemBarsPadding()
                                    .padding(top = 16.dp)
                            }
                        )
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.onboardingCompleted.not()) {
                        Gap(if (isCompact) 48.dp else 16.dp)
                    }
                    Text(
                        text = stringResource(R.string.what_kind_of_games_do_you_like),
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Gap(8.dp)
                    Text(
                        text = stringResource(R.string.select_at_least_3_genres).uppercase(),
                        color = PurpleGray30,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    val gridState = rememberLazyGridState()
                    val gridCells =
                        if (isCompact) GridCells.Fixed(2) else GridCells.Adaptive(200.dp)
                    LazyVerticalGrid(
                        columns = gridCells,
                        state = gridState,
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        content = {
                            items(
                                items = state.genres,
                                key = { it.id }
                            ) { genre ->

                                GenreCard(
                                    modifier = Modifier
                                        .clip(borderShape)
                                        .clickable {
                                            onAction(
                                                GenreAction.OnGenreSelectedClick(
                                                    genreIndex = state.genres.indexOf(genre)
                                                )
                                            )
                                        },
                                    genreItem = genre,
                                    borderShape = borderShape
                                )
                            }
                        }
                    )

                    GameAtlasActionButton(
                        text = stringResource(R.string.continue_button),
                        modifier = Modifier.padding(bottom = 16.dp),
                        enabled = state.canContinue,
                        onClick = {
                            onAction(GenreAction.OnContinueClick)
                        }
                    )
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun GenreScreenPreview() {
    GameAtlasTheme {
        GenreScreen(
            state = GenreState(
                genres = PreviewData.getGenreData()
            ),
            onAction = {}
        )
    }
}