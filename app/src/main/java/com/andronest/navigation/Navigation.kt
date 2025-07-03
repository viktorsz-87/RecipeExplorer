package com.andronest.navigation

sealed class Navigation(val route: String) {

    object Home: Navigation("Home")
    object Recipe: Navigation("Recipe/{idMeal}"){
        fun createRoute(idMeal: Int):String{
            return route.plus(idMeal)
        }
    }
    object Search: Navigation("Search")

    object Discover: Navigation("Discover")

}