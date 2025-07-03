package com.andronest.viewmodel

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
class DiscoverViewModel @Inject constructor(
    val repository: MealRepository
): ViewModel(){

    private var _discoverResponse = MutableStateFlow<List<Meal>>(emptyList())
    var discoverResponse: StateFlow<List<Meal>> = _discoverResponse.asStateFlow()

    init {
        fetchDiscoveryData()
    }

    private fun fetchDiscoveryData() {
        viewModelScope.launch {
            _discoverResponse.value = repository.getSingleRandomMeal()
        }
    }

}