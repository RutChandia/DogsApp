package cl.rutchandia.dogsapp.data.repository

import cl.rutchandia.dogsapp.data.mapper.DogsMapper
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.data.remote.DogsApiService
import cl.rutchandia.dogsapp.data.remote.doNetworkCall
import cl.rutchandia.dogsapp.domain.model.DogBreeds
import cl.rutchandia.dogsapp.domain.repository.DogBreedsRepository
import javax.inject.Inject

class DogBreedsRepositoryImpl @Inject constructor(
    private val apiService: DogsApiService, private val mapper: DogsMapper
) : DogBreedsRepository {

    override suspend fun getBreeds(): ApiResponseState<List<DogBreeds>> {
        return doNetworkCall {
            val response = apiService.getBreedsList()
            mapper.breedToDomain(response)
        }
    }
}