package com.mruraza.cleanarchitecture.DI

import com.mruraza.cleanarchitecture.Data.Remote.ApiService
import com.mruraza.cleanarchitecture.Data.Repository.ImageRepoImpl
import com.mruraza.cleanarchitecture.Domain.Repository.ImageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepo(apiService: ApiService):ImageRepo = ImageRepoImpl(apiService)
}