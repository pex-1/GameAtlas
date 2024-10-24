package com.example.feature.home.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Any> Flow<PagingData<T>>.collectAndHandleState(
    handleLoadStates: (LoadStates, Int) -> Unit
): LazyPagingItems<T> {
    val lazyPagingItem = collectAsLazyPagingItems()

    // Handle the different load state event
    val pagingLoadStates = lazyPagingItem.loadState.mediator ?: lazyPagingItem.loadState.source
    LaunchedEffect(pagingLoadStates) {
        handleLoadStates(pagingLoadStates, lazyPagingItem.itemCount)
    }

    return lazyPagingItem
}