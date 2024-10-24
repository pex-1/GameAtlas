package com.example.core.presentation.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core.presentation.designsystem.R

@Composable
fun PlaceholderImage(){
    Image(
        painterResource(id = R.drawable.placeholder),
        contentDescription = stringResource(R.string.placeholder_image),
        contentScale = ContentScale.Crop
    )
}