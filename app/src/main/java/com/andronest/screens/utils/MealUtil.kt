package com.andronest.screens.utils

import kotlin.reflect.full.memberProperties

fun getIngredientsWithMeasure(obj: Any): List<Pair<String,String>> {

    return (1..20).mapNotNull { i ->

        val ingredient = obj::class.memberProperties
            .firstOrNull { it.name == "strIngredient$i" }
            ?.let {
                it.call(obj) as? String
            }?.takeIf { it.isNotBlank() } ?: ""

        val measure = obj::class.memberProperties
            .firstOrNull{it.name == "strMeasure$i"}
            ?.let {
                it.call(obj) as? String
            }?.takeIf { it.isNotBlank() } ?: ""

        ingredient to measure
    }
}

fun getInstructions(obj: Any): String? {

    return obj::class.memberProperties.firstOrNull {
        it.name == "strInstructions"
    }?.let {
        it.call(obj) as? String
    }?.takeIf {
        it.isNotBlank()
    }

}