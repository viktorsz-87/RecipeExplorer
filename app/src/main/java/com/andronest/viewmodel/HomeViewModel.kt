package com.andronest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.Meal
import com.andronest.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private var _mealsResponse = MutableStateFlow<List<Meal>>(emptyList())
    var mealsResponse: StateFlow<List<Meal>> = _mealsResponse.asStateFlow()


    init {
        getMealByName("Beef")

    }

    var searchText by mutableStateOf("")

    var results by mutableStateOf<List<Meal>>(emptyList())


    fun searchMealByIngredient(ingredient: String) {
        if (ingredient.length < 3) {
            results = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                results = repository.getMealByMainIngredient(ingredient)
            } catch (e: Exception) {
                results = emptyList()
            }
        }
    }

    fun getMealByName(mealName: String) {
        viewModelScope.launch {
            _mealsResponse.value = repository.getMealByName(mealName)
        }
    }
}