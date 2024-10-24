package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.designsystem.PurpleGray30
import com.example.core.presentation.designsystem.PurpleGray60
import com.example.core.presentation.designsystem.GameAtlasTheme

@Composable
fun GameAtlasActionButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit,
    roundedCorners: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = PurpleGray30,
            disabledContentColor = PurpleGray60
        ),
        shape = if (roundedCorners) RoundedCornerShape(16.dp) else RoundedCornerShape(0.dp),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .alpha(if (enabled) 1f else 0.5f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = text,
                modifier = Modifier.alpha(if (isLoading) 0f else 1f),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Preview
@Composable
fun GreetingPreview() {
    GameAtlasTheme {
        GameAtlasActionButton(text = "CONTINUE", onClick = {})
    }
}