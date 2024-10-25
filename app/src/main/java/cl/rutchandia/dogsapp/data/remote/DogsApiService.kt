package cl.rutchandia.dogsapp.data.remote

import cl.rutchandia.dogsapp.data.model.DogImagesDTO
import cl.rutchandia.dogsapp.data.model.DogBreedsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiService {
    @GET("breeds/list/all")
    suspend fun getBreedsList(): DogBreedsDTO

    @GET("breed/{breed}/images")
    suspend fun getBreedImages(
        @Path("breed") breed: String
    ): DogImagesDTO
}