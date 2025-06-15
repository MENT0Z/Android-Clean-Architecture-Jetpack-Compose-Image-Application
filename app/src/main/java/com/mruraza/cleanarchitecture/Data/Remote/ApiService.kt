package com.mruraza.cleanarchitecture.Data.Remote

import com.mruraza.cleanarchitecture.Data.model.PixaBayResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("key") key: String = "my_API_Key",
        @Query("q") query: String
    ): PixaBayResponse
}