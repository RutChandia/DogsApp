package cl.rutchandia.dogsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cl.rutchandia.dogsapp.R
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogImage
import cl.rutchandia.dogsapp.domain.model.DogImages
import cl.rutchandia.dogsapp.presentation.components.CardImage
import cl.rutchandia.dogsapp.presentation.components.CenteredProgressIndicator
import cl.rutchandia.dogsapp.presentation.components.LoadingErrorBox
import cl.rutchandia.dogsapp.presentation.components.TryAgainButton
import cl.rutchandia.dogsapp.presentation.viewmodel.DogImagesViewModel

@Composable
fun DetailScreen(
    viewModel: DogImagesViewModel = hiltViewModel(), breed: String, navController: NavHostController
) {
    val breedImages by viewModel.dogImages.collectAsState()
    val apiState by viewModel.apiState

    LaunchedEffect(Unit) {
        viewModel.loadImages(breed)
    }

    DetailScreenContent(apiState = apiState,
        breedImages = breedImages,
        breed = breed,
        onTryAgain = viewModel::tryAgain,
        onNavigateBack = { navController.popBackStack() })

    LoadingErrorBox(
        state = apiState,
        onResetStatus = viewModel::resetApiStatus
    )
}

@Composable
private fun DetailScreenContent(
    apiState: ApiResponseState<DogImages>?,
    breedImages: List<DogImage>,
    breed: String,
    onTryAgain: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    when (apiState) {
        is ApiResponseState.Loading -> {
            CenteredProgressIndicator()
        }

        is ApiResponseState.Success -> {
            ImageList(
                breedImages = breedImages, onNavigateBack = onNavigateBack
            )
        }

        is ApiResponseState.Error -> {
            TryAgainButton(onTryAgain = { onTryAgain(breed) })
        }

        null -> {
            TryAgainButton(onTryAgain = { onTryAgain(breed) })
        }
    }
}

@Composable
private fun ImageList(
    breedImages: List<DogImage>, onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(colorScheme.background)
    ) {
        LazyColumn {
            items(breedImages) { breed ->
                CardImage(image = breed.image)

            }
        }
        ExtendedFloatingActionButton(
            onClick = { onNavigateBack() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            shape = CircleShape,
            containerColor = colorScheme.secondary,
            contentColor = colorScheme.surface,
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = stringResource(R.string.back_to_home),
                modifier = Modifier.padding(4.dp)
            )
            Text(
                stringResource(R.string.back),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
