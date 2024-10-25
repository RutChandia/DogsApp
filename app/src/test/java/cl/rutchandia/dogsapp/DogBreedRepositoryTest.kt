package cl.rutchandia.dogsapp

import cl.rutchandia.dogsapp.data.mapper.DogsMapper
import cl.rutchandia.dogsapp.data.model.DogBreedsDTO
import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.data.remote.DogsApiService
import cl.rutchandia.dogsapp.data.repository.DogBreedsRepositoryImpl
import cl.rutchandia.dogsapp.domain.model.DogBreed
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class DogBreedRepositoryTest {
    private val apiService: DogsApiService = mockk()
    private val mapper: DogsMapper = mockk()
    private val repository = DogBreedsRepositoryImpl(apiService, mapper)

    @Test
    fun testCallBreedsFromApiServiceAndMapToBreeds() = runBlocking {
        val dto = DogBreedsDTO(
            message = mapOf(
                "bulldog" to listOf("boston", "french"),
                "labrador" to emptyList()
            ), status = "success"
        )
        val expectedBreeds = listOf(
            DogBreed(name = "bulldog"),
            DogBreed(name = "labrador")
        )
        coEvery { apiService.getBreedsList() } returns dto
        coEvery { mapper.breedToDomain(dto) } returns expectedBreeds

        val result = repository.getBreeds()
        coVerify { apiService.getBreedsList() }
        val expectedResponse = ApiResponseState.Success(data = expectedBreeds)
        assertEquals(expectedResponse, result)
    }
}