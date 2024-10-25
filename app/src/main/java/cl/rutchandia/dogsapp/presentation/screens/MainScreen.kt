package cl.rutchandia.dogsapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import cl.rutchandia.dogsapp.R
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogBreed
import cl.rutchandia.dogsapp.presentation.components.CardBreedName
import cl.rutchandia.dogsapp.presentation.components.CenteredProgressIndicator
import cl.rutchandia.dogsapp.presentation.components.LoadingErrorBox
import cl.rutchandia.dogsapp.presentation.components.TryAgainButton
import cl.rutchandia.dogsapp.presentation.viewmodel.DogBreedsViewmodel

@Composable
fun MainScreen(
    viewModel: DogBreedsViewmodel = hiltViewModel(), navController: NavHostController
) {
    val dogBreeds = viewModel.dogBreedList.collectAsStateWithLifecycle()
    val apiState by viewModel.apiState

    MainScreenContent(
        breeds = dogBreeds.value ?: listOf(),
        onBreedSelected = { breed ->
            navController.navigate("detailScreen/$breed")
        },
        onTryAgain = viewModel::tryAgain,
        apiState = apiState
    )

    LoadingErrorBox(
        state = apiState, onResetStatus = viewModel::resetApiStatus
    )
}

@Composable
private fun MainScreenContent(
    breeds: List<DogBreed>,
    onBreedSelected: (String) -> Unit,
    onTryAgain: () -> Unit,
    apiState: ApiResponseState<*>?,
) {
    when (apiState) {
        is ApiResponseState.Loading -> {
            CenteredProgressIndicator()
        }

        is ApiResponseState.Success -> {
            if (breeds.isNotEmpty()) {
                BreedList(
                    breeds = breeds, onBreedSelected = onBreedSelected
                )
            }
        }

        is ApiResponseState.Error -> {
            TryAgainButton(onTryAgain)
        }

        null -> {
            TryAgainButton(onTryAgain)
        }
    }
}

@Composable
private fun BreedList(
    breeds: List<DogBreed>, onBreedSelected: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val filteredBreeds = breeds.filter { filter ->
        filter.name.contains(searchText, ignoreCase = true)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text(text = stringResource(R.string.search)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface.copy(alpha = 1.0f),
                unfocusedContainerColor = colorScheme.surface.copy(alpha = 1.0f),
                unfocusedIndicatorColor = colorScheme.primary.copy(alpha = 0.8f),
                unfocusedLabelColor = colorScheme.primary.copy(alpha = 0.8f),
            )

        )
        LazyColumn {
            items(filteredBreeds) { breed ->
                CardBreedName(breedName = breed.name, onItemClick = { selectedBreed ->
                    onBreedSelected(selectedBreed)
                })
            }
        }
    }
}


