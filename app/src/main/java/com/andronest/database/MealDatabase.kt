package com.andronest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andronest.model.Meal


@Database(entities = [Meal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {

    abstract val mealDao: MealDao

    companion object {

        @Volatile
        var mealDatabase: MealDatabase? = null

        fun getInstance(context: Context): MealDatabase {

            return mealDatabase ?: synchronized(this) {
                mealDatabase ?: buildDatabase(context).also { mealDatabase = it }
            }
        }

        private fun buildDatabase(context: Context): MealDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = MealDatabase::class.java,
                name = "meals_db"
            ).build()
        }
    }
}