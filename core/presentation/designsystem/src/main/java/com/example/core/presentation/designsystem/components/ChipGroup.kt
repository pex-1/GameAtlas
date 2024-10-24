package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    tag: List<String>,
    maxLines: Int = 1,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        maxLines = maxLines,
        horizontalArrangement = Arrangement.spacedBy(8.dp, horizontalAlignment),
        verticalArrangement = Arrangement.Center,
    ) {
        repeat(tag.size) {
            Chip(text = tag[it], hasBackground = true)
        }
    }
}