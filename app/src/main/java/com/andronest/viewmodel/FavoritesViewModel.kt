package com.andronest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.Meal
import com.andronest.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private var _allMeals = MutableStateFlow<List<Meal>>(emptyList())
    var allMeals = _allMeals.asStateFlow()

    init {
        fetchAllMealsFromDatabase()
    }

    fun fetchAllMealsFromDatabase(){

        viewModelScope.launch {
            _allMeals.value = mealRepository.mealDao.getAllMealsFromDatabase()
        }
    }
}