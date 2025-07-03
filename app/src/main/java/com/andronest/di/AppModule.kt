package com.andronest.di

import com.andronest.repository.MealRepository
import com.andronest.retrofit.Retrofit
import com.andronest.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideRetrofitApiServer(): RetrofitApi {
        return Retrofit.api
    }

    @Provides
    @Singleton
    fun provieRecipeRepository(retrofitApi: RetrofitApi): MealRepository{
        return MealRepository(retrofitApi)
    }
}