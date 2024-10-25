package cl.rutchandia.dogsapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogImage
import cl.rutchandia.dogsapp.domain.model.DogImages
import cl.rutchandia.dogsapp.domain.repository.DogImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DogImagesViewModel @Inject constructor(
    private val repository: DogImagesRepository
) : ViewModel() {
    var apiState = mutableStateOf<ApiResponseState<DogImages>?>(null)
        private set

    var dogImages = MutableStateFlow<List<DogImage>>(emptyList())
        private set

    fun loadImages(breed: String) {
        loadDogImages(breed)
    }

    private fun loadDogImages(breed: String) {
        apiState.value = ApiResponseState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBreedImages(breed)
            withContext(Dispatchers.Main) {
                if (response is ApiResponseState.Success) {
                    updateBreedImages(response.data.images)
                }
                apiState.value = response
            }
        }
    }

    private fun updateBreedImages(images: List<DogImage>) {
        dogImages.value = images
    }

    fun resetApiStatus() {
        apiState.value = null
    }

    fun tryAgain(breed: String) {
        loadDogImages(breed)
    }
}