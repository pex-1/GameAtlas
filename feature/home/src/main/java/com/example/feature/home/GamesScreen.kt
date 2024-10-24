package com.example.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.database.PreviewData
import com.example.core.domain.game.Game
import com.example.core.presentation.designsystem.ControllerIcon
import com.example.core.presentation.designsystem.GameAtlasTheme
import com.example.core.presentation.designsystem.components.ErrorListItem
import com.example.core.presentation.designsystem.components.GameAtlasBackground
import com.example.core.presentation.designsystem.components.GameAtlasEmptyState
import com.example.core.presentation.designsystem.components.GameAtlasScaffold
import com.example.core.presentation.designsystem.components.GameAtlasToolbar
import com.example.core.presentation.designsystem.components.LoadingListItem
import com.example.core.presentation.designsystem.components.ProgressIndicator
import com.example.core.presentation.designsystem.components.util.DevicePreviews
import com.example.core.presentation.designsystem.components.util.DropDownItem
import com.example.feature.home.components.GameItem
import com.example.feature.home.extensions.collectAndHandleState
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GamesScreenRoot(
    onGenreSelectionClick: () -> Unit,
    onGameClick: (Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: GamesViewModel = koinViewModel()
) {
    GamesScreen(
        viewModel.getAllGames.collectAndHandleState(viewModel::handleLoadState),
        viewModel.state,
        animatedVisibilityScope,
        sharedTransitionScope,
        onAction = { action ->
            when (action) {
                GamesAction.OnGenreSelectionClick -> onGenreSelectionClick()
                is GamesAction.OnGameClick -> {
                    onGameClick(action.gameId)
                }
                is GamesAction.IsLoadingGames -> {
                    viewModel.onAction(action)
                }
                is GamesAction.OnRefresh -> {
                    viewModel.onAction(action)
                }
                else -> Unit
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
private fun GamesScreen(
    gamePagingItems: LazyPagingItems<Game>,
    state: GameState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onAction: (GamesAction) -> Unit
) {
    GameAtlasScaffold(
        modifier = Modifier.fillMaxSize(),
        topAppBar = {
            GameAtlasToolbar(
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(GamesAction.OnGenreSelectionClick)
                    }
                },
                showBackButton = false,
                title = stringResource(R.string.toolbar_title),
                menuItems = listOf(DropDownItem(ControllerIcon, stringResource(R.string.genres)))
            )
        }
    ) { values ->
        GameAtlasBackground {

            val listState = rememberLazyListState()
            if (state.isLoading && state.showEmptyState.not()) {
                ProgressIndicator(diameter = 40.dp)
            } else if (state.showEmptyState && gamePagingItems.itemCount == 0) {
                GameAtlasEmptyState(isLoading = state.isLoading) {
                    gamePagingItems.refresh()
                    onAction(GamesAction.OnRefresh)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(values)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    state = listState
                ) {
                    items(count = gamePagingItems.itemCount) { index ->
                        val item = gamePagingItems[index]
                        item?.let { game ->

                            GameItem(
                                game = game,
                                animatedVisibilityScope = animatedVisibilityScope,
                                sharedTransitionScope = sharedTransitionScope,
                                onClick = {
                                    onAction(GamesAction.OnGameClick(game.id, game.name))
                                })
                        }
                    }

                    if (gamePagingItems.loadState.append is LoadState.Error || state.showEmptyState) {
                        item {
                            ErrorListItem {
                                gamePagingItems.retry()
                            }
                        }
                    } else if (gamePagingItems.itemCount > 0 && gamePagingItems.loadState.append.endOfPaginationReached.not()) {
                        item {
                            LoadingListItem()
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@DevicePreviews
@Composable
private fun GamesPreviewScreen() {
    GameAtlasTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                GamesScreen(
                    gamePagingItems = flowOf(PagingData.from(PreviewData.getGamesData())).collectAsLazyPagingItems(),
                    GameState(),
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                ) {
                }
            }
        }
    }
}