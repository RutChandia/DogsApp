package cl.rutchandia.dogsapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cl.rutchandia.dogsapp.R
import cl.rutchandia.dogsapp.data.remote.ApiResponseState

@Composable
fun LoadingErrorBox(
    state: ApiResponseState<*>?,
    onResetStatus: () -> Unit
) {
    if (state is ApiResponseState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
                .pointerInput(Unit) {},
            contentAlignment = Alignment.Center
        ) {
            CenteredProgressIndicator()
        }
    }
    if (state is ApiResponseState.Error) {
        ErrorDialog(
            title = stringResource(R.string.api_error),
            text = state.statusMessage.ifEmpty { stringResource(state.messageId) },
            buttonText = stringResource(R.string.accept),
            onDismiss = onResetStatus
        )
    }
}