package com.andronest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.MealResponse.Meal
import com.andronest.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: MealRepository
): ViewModel() {

    private var _mealsResponse = MutableStateFlow<List<Meal>>(emptyList())
    var mealsResponse: StateFlow<List<Meal>> = _mealsResponse.asStateFlow()

    init {
        fetchMealData()
    }

    private fun fetchMealData() {
        viewModelScope.launch {
            _mealsResponse.value = repository.getMealsByFirstLetter("a")
        }
    }

}