package cl.rutchandia.dogsapp.domain.repository

import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogImages

interface DogImagesRepository {
    suspend fun getBreedImages(breed: String): ApiResponseState<DogImages>
}