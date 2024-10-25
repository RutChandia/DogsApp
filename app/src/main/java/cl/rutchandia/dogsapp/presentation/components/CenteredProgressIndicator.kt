package cl.rutchandia.dogsapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenteredProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Cube(
            modifier = Modifier.align(Alignment.Center),
        )
    }
}