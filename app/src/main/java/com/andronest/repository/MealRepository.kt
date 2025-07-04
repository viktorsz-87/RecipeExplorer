package com.andronest.repository

import android.util.Log
import com.andronest.model.MealResponse
import com.andronest.retrofit.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(
    val retrofitApi: RetrofitApi) {

    suspend fun getSingleRandomMeal(): List<MealResponse.Meal>{

        return try {
            retrofitApi.getSingleRandomMeal().meals
        } catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }
    suspend fun getMealsByFirstLetter(letter: String):List<MealResponse.Meal>{

        return try {
            retrofitApi.searchMealsByFirstLetter(letter).meals
        } catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }
}