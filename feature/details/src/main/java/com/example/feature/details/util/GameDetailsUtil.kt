package com.example.feature.details.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.domain.enums.PlatformType
import com.example.core.presentation.designsystem.AndroidIcon
import com.example.core.presentation.designsystem.AppleIcon
import com.example.core.presentation.designsystem.LinuxIcon
import com.example.core.presentation.designsystem.NintendoSwitchIcon
import com.example.core.presentation.designsystem.PlaystationIcon
import com.example.core.presentation.designsystem.WiiIcon
import com.example.core.presentation.designsystem.WindowsIcon
import com.example.core.presentation.designsystem.XboxIcon

@Composable
fun PlatformType.getPlatformImage(): ImageVector? {
    return when (this) {
        PlatformType.PLAYSTATION -> PlaystationIcon
        PlatformType.XBOX -> XboxIcon
        PlatformType.WINDOWS -> WindowsIcon
        PlatformType.NINTENDO_SWITCH -> NintendoSwitchIcon
        PlatformType.ANDROID -> AndroidIcon
        PlatformType.APPLE -> AppleIcon
        PlatformType.LINUX -> LinuxIcon
        PlatformType.WII -> WiiIcon
        PlatformType.OTHER -> null
    }
}