package com.andronest.model

data class MealWithInstructions(
    val ingredientsWithMeasure: List<Pair<String, String>>,
    val instructions: String?
)