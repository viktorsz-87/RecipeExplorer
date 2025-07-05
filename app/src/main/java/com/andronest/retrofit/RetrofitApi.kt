package com.andronest.retrofit


import com.andronest.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface RetrofitApi{

    @GET("lookup.php")
    suspend fun lookupMealById(@Query("i") id:String): MealResponse

    @GET("search.php")
    suspend fun searchMealsByFirstLetter(@Query("f") firstLetter: String): MealResponse

    @GET("random.php")
    suspend fun getSingleRandomMeal(): MealResponse

}