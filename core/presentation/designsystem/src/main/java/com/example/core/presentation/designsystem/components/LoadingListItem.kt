package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.designsystem.GameAtlasTheme

@Composable
fun LoadingListItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(
                top = 16.dp,
                bottom = 16.dp
            ),
    ) {
        ProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingListItemPreview() {
    GameAtlasTheme {
        LoadingListItem()
    }
}