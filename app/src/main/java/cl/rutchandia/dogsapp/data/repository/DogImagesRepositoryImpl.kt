package cl.rutchandia.dogsapp.data.repository

import cl.rutchandia.dogsapp.data.mapper.DogsMapper
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.data.remote.DogsApiService
import cl.rutchandia.dogsapp.data.remote.doNetworkCall
import cl.rutchandia.dogsapp.domain.model.DogImages
import cl.rutchandia.dogsapp.domain.repository.DogImagesRepository
import javax.inject.Inject

class DogImagesRepositoryImpl @Inject constructor(
    private val apiService: DogsApiService, private val mapper: DogsMapper
) : DogImagesRepository {

    override suspend fun getBreedImages(
        breed: String
    ): ApiResponseState<DogImages> {
        return doNetworkCall {
            val response = apiService.getBreedImages(breed = breed)
            mapper.imagesToDomain(response)
        }
    }
}