package com.andronest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    Object:
    Singleton, and itself initializes in a thread-safe manner
    Don't use class, it allows for multiple instances.
 */
object Retrofit {

    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    // lazy is thread-safe by default
    // subsequent access returns same cached value

    val api: RetrofitApi by lazy {
      
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
}
