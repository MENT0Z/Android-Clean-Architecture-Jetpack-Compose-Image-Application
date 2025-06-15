package com.mruraza.cleanarchitecture.Domain.Repository

import com.mruraza.cleanarchitecture.Domain.model.DomainModel

interface ImageRepo {
    suspend fun getImages(query: String): Result<List<DomainModel>>
}