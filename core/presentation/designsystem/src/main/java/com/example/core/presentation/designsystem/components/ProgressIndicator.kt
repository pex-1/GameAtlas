package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    strokeWidth: Dp = 1.dp,
    diameter: Dp = 20.dp
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = alignment
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(diameter),
            strokeWidth = strokeWidth,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
