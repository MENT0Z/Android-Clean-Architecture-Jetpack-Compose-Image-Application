package com.mruraza.cleanarchitecture.Data.Repository

import com.mruraza.cleanarchitecture.Data.Remote.ApiService
import com.mruraza.cleanarchitecture.Domain.Repository.ImageRepo
import com.mruraza.cleanarchitecture.Domain.model.DomainModel
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(private val apiService: ApiService) : ImageRepo {
    override suspend fun getImages(query: String): Result<List<DomainModel>> {
        return try{
            val response = apiService.getImages(query=query)
            val listOfDomainModel = response.hits.map { DomainModel(imageUrl = it.largeImageURL) }
            Result.success(listOfDomainModel)
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}