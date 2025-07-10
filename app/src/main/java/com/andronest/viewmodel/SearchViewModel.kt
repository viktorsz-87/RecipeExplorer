package com.andronest.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.Meal
import com.andronest.repository.MealRepository
import com.andronest.screens.utils.ConnectivityStatus
import com.andronest.screens.utils.MealConstants
import com.andronest.screens.utils.MealConstants.OFFLINE_MODE
import com.andronest.screens.utils.MealConstants.ONLINE_MODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MealRepository
): ViewModel() {

    private val _networkStatus: MutableStateFlow<ConnectivityStatus> = MutableStateFlow(ConnectivityStatus.Loading)
    val networkStatus = _networkStatus.asStateFlow()

    val categories = MealConstants.CATEGORIES

    val modes = listOf(ONLINE_MODE, OFFLINE_MODE)

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private val _mealsByCategory = MutableStateFlow<List<Meal>>(emptyList())
    val mealsByCategory = _mealsByCategory.asStateFlow()

    private val _searchText = mutableStateOf("")
    val searchText: String by _searchText

    private val _selectedCategory = mutableStateOf("")
    val selectedCategory: String by _selectedCategory

    private val _selectedMode = mutableStateOf("")
    val selectedMode: String by _selectedMode

    private var searchJob: Job? = null

    init {
        _selectedMode.value = ONLINE_MODE
        _selectedCategory.value = MealConstants.DEFAULT_CATEGORY
    }

    val isOfflineWithMeals: Boolean
        get() = _selectedMode.value == OFFLINE_MODE && !_meals.value.isNullOrEmpty()

    val isOnlineFilteredWithResults: Boolean
        get() = isFilterEnabled && _selectedMode.value == ONLINE_MODE && !_mealsByCategory.value.isNullOrEmpty()


    fun fetchAllMealsFromDatabase(){
        viewModelScope.launch {
           _meals.value = repository.mealDao.getAllMealsFromDatabase()
        }
    }

    val isFilterEnabled: Boolean
        get() = selectedCategory != MealConstants.DEFAULT_CATEGORY


    fun updateMode(mode: String){
        _selectedMode.value = mode
    }

    fun updateCategory(category: String?){
        _selectedCategory.value = category ?: ""
    }


    @OptIn(FlowPreview::class)
    fun searchMealsByCategory(mealCategory: String){

        if(!categories.contains(mealCategory)){
            _mealsByCategory.value = emptyList()
            return
        }

        try {
            viewModelScope.launch {
                _mealsByCategory.value = repository.getMealByCategory(mealCategory)
            }

        } catch (error: Exception){ }
    }

    @OptIn(FlowPreview::class)
    fun searchMealByName(query: String){

        if(_selectedMode.value==OFFLINE_MODE){
            _searchText.value = query
            searchJob?.cancel()
            fetchAllMealsFromDatabase()
            return
        }

        _searchText.value = query
        searchJob?.cancel()

        try {
            if(query.isNotEmpty()){
                searchJob = viewModelScope.launch {

                    delay(150) // debounce to not hog api calls
                    _meals.value = repository.getMealByName(query)
                }
            } else {
                _meals.value = emptyList()
            }

        } catch (error: Exception){
            Log.d("AppError","error: " + error.message)
            _meals.value = emptyList()
        }
    }
}