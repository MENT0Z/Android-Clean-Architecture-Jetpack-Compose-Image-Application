package com.mruraza.cleanarchitecture.Data.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance {
//    fun getInstance():Retrofit{
//        return Retrofit.Builder()
//            .baseUrl("https://pixabay.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    fun getApiService():ApiService{
//        return getInstance().create(ApiService::class.java)
//    }
//}