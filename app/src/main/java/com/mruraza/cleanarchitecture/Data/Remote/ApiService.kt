package com.mruraza.cleanarchitecture.Data.Remote

import com.mruraza.cleanarchitecture.Data.model.PixaBayResponse
import retrofit2.http.GET
import retrofit2.http.Query

//https://pixabay.com/api/?key=50850414-f788240405c4db19068fe6828&q=yellow+flowers&image_type=photo
interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("key") key: String = "50850414-f788240405c4db19068fe6828",
        @Query("q") query: String
    ): PixaBayResponse
}