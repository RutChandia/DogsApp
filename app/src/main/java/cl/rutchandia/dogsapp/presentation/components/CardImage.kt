package cl.rutchandia.dogsapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CardImage(image: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ), shape = RoundedCornerShape(4.dp), colors = CardDefaults.cardColors(
            containerColor = colorScheme.surfaceBright,
        )
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .width(500.dp)
                .padding(4.dp)
        )
    }
}