package com.andronest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andronest.model.Meal


@Dao
interface MealDao {

    @Query("SELECT * FROM meals")
    suspend fun getAllMealsFromDatabase(): List<Meal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealToDatabase(meal: Meal)
}