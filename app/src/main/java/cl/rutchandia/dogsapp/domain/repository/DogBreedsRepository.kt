package cl.rutchandia.dogsapp.domain.repository

import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogBreeds

interface DogBreedsRepository {
    suspend fun getBreeds(): ApiResponseState<List<DogBreeds>>
}