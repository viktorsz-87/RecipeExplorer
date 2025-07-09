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

    suspend fun getMealByCategory(mealCategory: String): List<Meal>{
        return try {
            retrofitApi.filterByCategory(mealCategory).meals
        }catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }

    suspend fun getMealByMainIngredient(ingredient: String): List<Meal>{
        return try {
            retrofitApi.filterByMainIngredient(ingredient).meals
        }catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }

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
    suspend fun getMealByFirstLetter(letter: String):List<Meal>{

        return try {
            retrofitApi.searchMealByFirstLetter(letter).meals
        } catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }
    suspend fun getMealByName(mealName: String):List<Meal>{

        return try {
            retrofitApi.searchMealByName(mealName).meals
        } catch (e: Exception){
            Log.d("MyTAG",e.message.toString())
            emptyList()
        }
    }

}