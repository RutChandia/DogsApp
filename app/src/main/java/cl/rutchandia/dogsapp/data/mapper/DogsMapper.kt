package cl.rutchandia.dogsapp.data.mapper

import cl.rutchandia.dogsapp.data.model.DogBreedsDTO
import cl.rutchandia.dogsapp.data.model.DogImagesDTO
import cl.rutchandia.dogsapp.domain.model.DogBreed
import cl.rutchandia.dogsapp.domain.model.DogImage
import cl.rutchandia.dogsapp.domain.model.DogImages
import javax.inject.Inject

class DogsMapper @Inject constructor() {
    fun breedToDomain(dto: DogBreedsDTO): List<DogBreed> {
        return dto.message.map { (breed, _) ->
            DogBreed(name = breed)
        }
    }

    fun imagesToDomain(
        dto: DogImagesDTO
    ): DogImages {
        val images = dto.message.map { DogImage(it) }
        return DogImages(images = images)
    }
}
