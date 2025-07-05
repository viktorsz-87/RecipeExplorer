package com.andronest.navigation

sealed class Navigation(val route: String) {

    object Home: Navigation("Home")
    object Recipe: Navigation("Recipe/{idMeal}"){
        fun createRoute(idMeal: Int):String = "Recipe/$idMeal"
    }
    object Search: Navigation("Search")

    object Discover: Navigation("Discover?mealId={mealId}"){
        fun createRoute(mealId: String? = null): String{
            return if(mealId != null) "Discover?mealId=$mealId" else "Discover"
        }
    }

    // Just a helper function to get base route without args
    fun getBaseRoute() = route.substringBefore("/")
}