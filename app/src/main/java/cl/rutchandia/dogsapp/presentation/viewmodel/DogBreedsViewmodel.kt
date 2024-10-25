package cl.rutchandia.dogsapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogBreeds
import cl.rutchandia.dogsapp.domain.repository.DogBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DogBreedsViewmodel @Inject constructor(
    private val repository: DogBreedsRepository
) : ViewModel() {

    var apiState = mutableStateOf<ApiResponseState<List<DogBreeds>>?>(null)
        private set
    var dogBreedList = MutableStateFlow<List<DogBreeds>?>(null)
        private set

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        apiState.value = ApiResponseState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBreeds()
            withContext(Dispatchers.Main) {
                if (response is ApiResponseState.Success) {
                    updateDogBreeds(response.data)
                }
                apiState.value = response
            }
        }
    }

    private fun updateDogBreeds(list: List<DogBreeds>) {
        dogBreedList.value = list
    }

    fun resetApiStatus() {
        apiState.value = null
    }

    fun tryAgain() {
        fetchBreeds()
    }
}

