package cl.rutchandia.dogsapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    buttonText: String,
    onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismiss() }, title = {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }, text = { Text(text = text, fontSize = 14.sp) }, confirmButton = {
        Button(
            onClick = onDismiss, modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    fontSize = 16.sp,
                ),
            )
        }
    })
}