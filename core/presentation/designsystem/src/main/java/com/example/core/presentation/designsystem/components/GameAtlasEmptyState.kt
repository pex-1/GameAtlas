package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.designsystem.BrokenControllerIcon
import com.example.core.presentation.designsystem.Neutral20
import com.example.core.presentation.designsystem.Neutral50
import com.example.core.presentation.designsystem.Neutral70
import com.example.core.presentation.designsystem.GameAtlasTheme
import com.example.core.presentation.designsystem.R

@Composable
fun GameAtlasEmptyState(
    buttonEnabled: Boolean = true,
    title: String = stringResource(R.string.no_data_found),
    subtitle: String = stringResource(R.string.unable_to_load_data),
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            GameAtlasActionButton(
                enabled = buttonEnabled,
                text = stringResource(R.string.retry),
                onClick = onClick,
                roundedCorners = false,
                isLoading = isLoading
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .height(70.dp),
                imageVector = BrokenControllerIcon,
                contentDescription = stringResource(R.string.broken_controller_icon),
                tint = Neutral70
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = Neutral20
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Neutral50
            )
        }
    }


}


@Preview
@Composable
private fun GameAtlasEmptyStatePreview() {
    GameAtlasTheme {
        GameAtlasEmptyState(onClick = {})
    }
}