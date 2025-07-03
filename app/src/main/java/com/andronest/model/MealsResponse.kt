package com.andronest.model

import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)

data class Meal(
    @SerializedName("idMeal")
    val id: String?,

    @SerializedName("strMeal")
    val name: String?,

    @SerializedName("strMealThumb")
    val thumbnailUrl: String?,

    @SerializedName("strCategory")
    val category: String? = null,

    @SerializedName("strInstructions")
    val instructions: String? = null
)



