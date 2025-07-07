package com.andronest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andronest.model.Meal
import com.andronest.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    fun insertIntoDatabase(meal: Meal){
        viewModelScope.launch {
            mealRepository.mealDao.insertMealToDatabase(meal)
        }
    }

    fun deleteFromDatabase(meal: Meal){
        viewModelScope.launch {
            mealRepository.mealDao.deleteMealFromDatabase(meal)
        }
    }

    private val _snackBarState = MutableStateFlow<SnackBarState>(SnackBarState.Hidden)
    val snackBarState = _snackBarState.asStateFlow()

    sealed class SnackBarState(){
        object Hidden: SnackBarState()
        data class Visible(val message: String): SnackBarState()
    }

    fun showSnackBar(msg: String){

        _snackBarState.value = SnackBarState.Visible(msg)

        viewModelScope.launch {
            delay(3000)
            _snackBarState.value = SnackBarState.Hidden
        }
    }

    fun hideSnackBar(){
        _snackBarState.value = SnackBarState.Hidden
    }
}