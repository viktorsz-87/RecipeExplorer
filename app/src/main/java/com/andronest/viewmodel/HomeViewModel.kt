package com.andronest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.Meal
import com.andronest.repository.MealRepository
import com.andronest.screens.utils.MealConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _errorEvents = Channel<String>()
    val errorEvents = _errorEvents.receiveAsFlow()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null


    init {
        loadDefaultMeals()
    }


    fun searchMealByIngredient(ingredient: String) {

        _uiState.update{
            it.copy(searchQuery = ingredient)
        }

        searchJob?.cancel()

        if (ingredient.length < MealConstants.MIN_SEARCH_LENGTH) {
            _uiState.update {
                it.copy(searchResults = emptyList(), searchError = null)
            }
            return
        }

        searchJob = viewModelScope.launch {
            try {
               val results = repository.getMealByMainIngredient(ingredient)

                _uiState.update { it.copy(searchResults = results, isSearching = false) }
            } catch (e: Exception) {
               _uiState.update { it.copy(isSearching = false, searchError = e.message ?: "Search failed" )  }

                _errorEvents.send(e.message ?: "Error searching for meal")
            }
        }
    }

    private fun loadDefaultMeals(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val meals = repository.getMealByName(MealConstants.DEFAULT_SEARCH_TERM)
                _uiState.update { it.copy(meals = meals, isLoading = false, error = null) }
            } catch (e: Exception){
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Failed to load meals") }
            }
        }
    }

    // State container
    data class HomeUiState(
        val meals: List<Meal> = emptyList(),
        val searchQuery: String = "",
        val searchResults: List<Meal> = emptyList(),
        val isLoading: Boolean = false,
        val isSearching: Boolean = false,
        val error: String? = null,
        val searchError: String? = null
    )
}