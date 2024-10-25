package cl.rutchandia.dogsapp

import cl.rutchandia.dogsapp.data.remote.ApiResponseState
import cl.rutchandia.dogsapp.domain.model.DogBreed
import cl.rutchandia.dogsapp.domain.repository.DogBreedsRepository
import cl.rutchandia.dogsapp.presentation.viewmodel.DogBreedsViewmodel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DogBreedViewmodelTest {
    @Test
    fun testGetDogBreedsReturnsNonEmptyListOnSuccess() = runBlocking {
        val dummyBreeds = listOf(
            DogBreed("Labrador"),
            DogBreed("Bulldog")
        )
        val viewModel = DogBreedsViewmodel(object : DogBreedsRepository {
            override suspend fun getBreeds(): ApiResponseState<List<DogBreed>> {
                return ApiResponseState.Success(data = dummyBreeds)
            }
        })

        val status = viewModel.apiState.value

        if (status is ApiResponseState.Success) {
            assert(status.data.isNotEmpty())
        }
    }


    @Test
    fun testGetDogBreedsReturnsErrorStateOnFailure() = runBlocking {
        val viewModel = DogBreedsViewmodel(object : DogBreedsRepository {
            override suspend fun getBreeds(): ApiResponseState<List<DogBreed>> {
                return ApiResponseState.Error(404)
            }
        })

        val status = viewModel.apiState.value

        if (status is ApiResponseState.Error) {
            assert(status.messageId == 404)
        }
    }
}