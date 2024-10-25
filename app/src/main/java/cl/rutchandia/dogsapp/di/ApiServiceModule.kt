package cl.rutchandia.dogsapp.di

import cl.rutchandia.dogsapp.data.remote.DogsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    private const val BASE_URL = "https://dog.ceo/api/"

    @Provides
    fun provideApiService(retrofit: Retrofit): DogsApiService {
        return retrofit.create(DogsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}