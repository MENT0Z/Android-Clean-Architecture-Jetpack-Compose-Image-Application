package com.mruraza.cleanarchitecture.Domain.UseCases

import com.mruraza.cleanarchitecture.Domain.Repository.ImageRepo
import com.mruraza.cleanarchitecture.Domain.model.DomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetImageUseCase @Inject constructor(private val imageRepo: ImageRepo) {
    operator fun invoke(query: String) = flow<Result<List<DomainModel>>> {
        val response = imageRepo.getImages(query)
        emit(response)
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}