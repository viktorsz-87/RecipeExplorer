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
class DiscoverViewModel @Inject constructor(
    val repository: MealRepository
): ViewModel(){

    private var _discoverResponse = MutableStateFlow<List<Meal>>(emptyList())
    var discoverResponse: StateFlow<List<Meal>> = _discoverResponse.asStateFlow()

    private var _fetchMealByIdResponse = MutableStateFlow<List<Meal>>(emptyList())
    var fetchMealByIdResponse = _fetchMealByIdResponse.asStateFlow()


    init {
        fetchDiscoveryData()
    }

    fun fetchMealById(id: String){
        viewModelScope.launch {
            _fetchMealByIdResponse.value = repository.getMealById(id)
        }
    }

    private fun fetchDiscoveryData() {
        viewModelScope.launch {
            _discoverResponse.value = repository.getSingleRandomMeal()
        }
    }

}