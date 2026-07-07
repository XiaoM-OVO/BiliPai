package com.android.purebilibili.core.ui.transition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

@Composable
internal fun VideoCardTransitionNavBackdrop(
    visible: Boolean,
    progress: Float,
    phase: VideoCardTransitionBackgroundPhase,
    isLightBackground: Boolean,
    modifier: Modifier = Modifier,
    baseBackgroundColor: Color = MaterialTheme.colorScheme.background,
) {
    if (!visible) return
    val frame = remember(progress, phase, isLightBackground) {
        resolveVideoCardTransitionNavBackdropFrame(
            progress = progress,
            phase = phase,
            isLightBackground = isLightBackground,
        )
    }
    val backdropColor = remember(baseBackgroundColor, frame) {
        resolveVideoCardTransitionNavBackdropColor(
            baseBackgroundColor = baseBackgroundColor,
            frame = frame,
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(backdropColor)
            },
    )
}
