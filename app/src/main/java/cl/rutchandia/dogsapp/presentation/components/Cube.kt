package cl.rutchandia.dogsapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Cube(modifier: Modifier) {
    val rotation = remember { mutableFloatStateOf(0f) }
    Box(
        modifier
            .size(
                60.dp, 60.dp
            )
            .graphicsLayer(rotationZ = rotation.floatValue,
                cameraDistance = LocalDensity.current.run { 16.dp.toPx() })
            .background(Color.Transparent)
            .border(4.dp, colorScheme.primary)
    ) {
        Box(
            modifier
                .size(
                    60.dp, 60.dp
                )
                .graphicsLayer(rotationZ = rotation.floatValue,
                    cameraDistance = LocalDensity.current.run { 16.dp.toPx() })
                .background(Color.Transparent)
                .border(4.dp, colorScheme.secondary)
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos { frameTime -> rotation.floatValue = (frameTime / 8_333_333L) % 360f }
        }
    }
}

