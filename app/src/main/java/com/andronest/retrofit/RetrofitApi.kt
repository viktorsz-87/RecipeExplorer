package com.andronest.retrofit


import com.andronest.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface RetrofitApi{

    @GET("lookup.php")
    suspend fun lookupMealById(@Query("i") id:String): MealResponse

    @GET("filter.php")
    suspend fun filterByMainIngredient(@Query("i") ingredient: String): MealResponse

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): MealResponse

    @GET("search.php")
    suspend fun searchMealByFirstLetter(@Query("f") firstLetter: String): MealResponse

    @GET("search.php")
    suspend fun searchMealByName(@Query("s") mealName: String): MealResponse

    @GET("random.php")
    suspend fun getSingleRandomMeal(): MealResponse

}