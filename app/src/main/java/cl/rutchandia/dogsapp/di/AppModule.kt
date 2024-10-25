package cl.rutchandia.dogsapp.di

import cl.rutchandia.dogsapp.data.repository.DogImagesRepositoryImpl
import cl.rutchandia.dogsapp.data.repository.DogBreedsRepositoryImpl
import cl.rutchandia.dogsapp.domain.repository.DogBreedsRepository
import cl.rutchandia.dogsapp.domain.repository.DogImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindDogBreedsRepository(repository: DogBreedsRepositoryImpl): DogBreedsRepository

    @Binds
    abstract fun bindDogImagesRepository(repository: DogImagesRepositoryImpl): DogImagesRepository
}
