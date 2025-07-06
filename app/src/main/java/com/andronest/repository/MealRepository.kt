package com.andronest.repository

import android.util.Log
import com.andronest.database.MealDao
import com.andronest.model.Meal
import com.andronest.retrofit.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    val mealDao: MealDao) {

    suspend fun getMealById(mealId: String): List<Meal>{
        return try {
            retrofitApi.lookupMealById(mealId).meals
        }catch (e: Exception){

            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }
    suspend fun getSingleRandomMeal(): List<Meal>{

        return try {
            retrofitApi.getSingleRandomMeal().meals
        } catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }
    suspend fun getMealsByFirstLetter(letter: String):List<Meal>{

        return try {
            retrofitApi.searchMealsByFirstLetter(letter).meals
        } catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }
}