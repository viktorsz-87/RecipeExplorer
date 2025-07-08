package com.andronest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)

@Entity(tableName = "meals")
data class Meal(

    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    @SerializedName("idMeal")
    val idMeal: String,

    @SerializedName("dateModified")
    @ColumnInfo(name = "date_modified")
    val dateModified: String? = null,

    @SerializedName("strArea")
    @ColumnInfo(name = "area")
    val area: String? = null,

    @SerializedName("strCategory")
    @ColumnInfo(name = "category")
    val category: String? = null,

    @SerializedName("strCreativeCommonsConfirmed")
    @ColumnInfo(name = "creative_commons_confirmed")
    val creativeCommonsConfirmed: String? = null,

    @SerializedName("strImageSource")
    @ColumnInfo(name = "image_source")
    val imageSource: String? = null,

    @SerializedName("strIngredient")
    @ColumnInfo(name = "ingredient")
    val ingredient: String? = null,

    @SerializedName("strIngredient1")
    @ColumnInfo(name = "ingredient1")
    val ingredient1: String? = null,

    @SerializedName("strIngredient10")
    @ColumnInfo(name = "ingredient10")
    val ingredient10: String? = null,

    @SerializedName("strIngredient11")
    @ColumnInfo(name = "ingredient11")
    val ingredient11: String? = null,

    @SerializedName("strIngredient12")
    @ColumnInfo(name = "ingredient12")
    val ingredient12: String? = null,

    @SerializedName("strIngredient13")
    @ColumnInfo(name = "ingredient13")
    val ingredient13: String? = null,

    @SerializedName("strIngredient14")
    @ColumnInfo(name = "ingredient14")
    val ingredient14: String? = null,

    @SerializedName("strIngredient15")
    @ColumnInfo(name = "ingredient15")
    val ingredient15: String? = null,

    @SerializedName("strIngredient16")
    @ColumnInfo(name = "ingredient16")
    val ingredient16: String? = null,

    @SerializedName("strIngredient17")
    @ColumnInfo(name = "ingredient17")
    val ingredient17: String? = null,

    @SerializedName("strIngredient18")
    @ColumnInfo(name = "ingredient18")
    val ingredient18: String? = null,

    @SerializedName("strIngredient19")
    @ColumnInfo(name = "ingredient19")
    val ingredient19: String? = null,

    @SerializedName("strIngredient2")
    @ColumnInfo(name = "ingredient2")
    val ingredient2: String? = null,

    @SerializedName("strIngredient20")
    @ColumnInfo(name = "ingredient20")
    val ingredient20: String? = null,

    @SerializedName("strIngredient3")
    @ColumnInfo(name = "ingredient3")
    val ingredient3: String? = null,

    @SerializedName("strIngredient4")
    @ColumnInfo(name = "ingredient4")
    val ingredient4: String? = null,

    @SerializedName("strIngredient5")
    @ColumnInfo(name = "ingredient5")
    val ingredient5: String? = null,

    @SerializedName("strIngredient6")
    @ColumnInfo(name = "ingredient6")
    val ingredient6: String? = null,

    @SerializedName("strIngredient7")
    @ColumnInfo(name = "ingredient7")
    val ingredient7: String? = null,

    @SerializedName("strIngredient8")
    @ColumnInfo(name = "ingredient8")
    val ingredient8: String? = null,

    @SerializedName("strIngredient9")
    @ColumnInfo(name = "ingredient9")
    val ingredient9: String? = null,

    @SerializedName("strInstructions")
    @ColumnInfo(name = "instructions")
    val instructions: String? = null,

    @SerializedName("strMeal")
    @ColumnInfo(name = "meal")
    val meal: String? = null,

    @SerializedName("strMealAlternate")
    @ColumnInfo(name = "meal_alternative")
    val mealAlternate: String? = null,

    @SerializedName("strMealThumb")
    @ColumnInfo(name = "meal_thumb")
    val mealThumb: String? = null,

    @SerializedName("strMeasure1")
    @ColumnInfo(name = "measure1")
    val measure1: String? = null,

    @SerializedName("strMeasure10")
    @ColumnInfo(name = "measure10")
    val measure10: String? = null,

    @SerializedName("strMeasure11")
    @ColumnInfo(name = "measure11")
    val measure11: String? = null,

    @SerializedName("strMeasure12")
    @ColumnInfo(name = "measure12")
    val measure12: String? = null,

    @SerializedName("strMeasure13")
    @ColumnInfo(name = "measure13")
    val measure13: String? = null,

    @SerializedName("strMeasure14")
    @ColumnInfo(name = "measure14")
    val measure14: String? = null,

    @SerializedName("strMeasure15")
    @ColumnInfo(name = "measure15")
    val measure15: String? = null,

    @SerializedName("strMeasure16")
    @ColumnInfo(name = "measure16")
    val measure16: String? = null,

    @SerializedName("strMeasure17")
    @ColumnInfo(name = "measure17")
    val measure17: String? = null,

    @SerializedName("strMeasure18")
    @ColumnInfo(name = "measure18")
    val measure18: String? = null,

    @SerializedName("strMeasure19")
    @ColumnInfo(name = "measure19")
    val measure19: String? = null,

    @SerializedName("strMeasure2")
    @ColumnInfo(name = "measure2")
    val measure2: String? = null,

    @SerializedName("strMeasure20")
    @ColumnInfo(name = "measure20")
    val measure20: String? = null,

    @SerializedName("strMeasure3")
    @ColumnInfo(name = "measure3")
    val measure3: String? = null,

    @SerializedName("strMeasure4")
    @ColumnInfo(name = "measure4")
    val measure4: String? = null,

    @SerializedName("strMeasure5")
    @ColumnInfo(name = "measure5")
    val measure5: String? = null,

    @SerializedName("strMeasure6")
    @ColumnInfo(name = "measure6")
    val measure6: String? = null,

    @SerializedName("strMeasure7")
    @ColumnInfo(name = "measure7")
    val measure7: String? = null,

    @SerializedName("strMeasure8")
    @ColumnInfo(name = "measure8")
    val measure8: String? = null,

    @SerializedName("strMeasure9")
    @ColumnInfo(name = "measure9")
    val measure9: String? = null,

    @SerializedName("strSource")
    @ColumnInfo(name = "source")
    val source: String? = null,

    @SerializedName("strTags")
    @ColumnInfo(name = "tags")
    val tags: String? = null,

    @SerializedName("strYoutube")
    @ColumnInfo(name = "youtube")
    val youtube: String? = null
)