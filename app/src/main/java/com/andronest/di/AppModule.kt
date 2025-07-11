package com.andronest.di

import android.content.Context
import android.net.ConnectivityManager
import com.andronest.database.MealDao
import com.andronest.database.MealDatabase
import com.andronest.repository.MealRepository
import com.andronest.retrofit.Retrofit
import com.andronest.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideRetrofitApiService(): RetrofitApi {
        return Retrofit.api
    }

    @Provides
    @Singleton
    fun provideMealRepository(retrofitApi: RetrofitApi,
                              mealDao: MealDao): MealRepository{

        return MealRepository(retrofitApi, mealDao)
    }

    @Provides
    @Singleton
    fun provideMealDatabase(@ApplicationContext context: Context): MealDatabase{
        return MealDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideMealDao(mealDatabase: MealDatabase): MealDao{
        return mealDatabase.mealDao
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager{

        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}