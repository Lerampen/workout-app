package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.AppDatabase
import com.example.workoutapp.data.Meal
import com.example.workoutapp.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    application: Application ,
    private val repository: MealRepository
    ): AndroidViewModel(application = application) {


  private val _allMeals = MutableStateFlow<List<Meal>>(emptyList())
           var allMeals : StateFlow<List<Meal>> = _allMeals
    init {

        viewModelScope.launch{
            repository.getAllMeals().collect{ meals->
                _allMeals.value = meals
            }
        }
    }

    fun insert(meal: Meal) = viewModelScope.launch {
        repository.insertMeal(meal)
        repository.getAllMeals().collect{ meals->
            _allMeals.value = meals
        }
    }

    fun delete(meal: Meal) = viewModelScope.launch {
        repository.deleteMeal(meal)
        repository.getAllMeals().collect{ meals->
            _allMeals.value = meals
        }
    }
    // Function for setting sample data in preview
    fun setSampleMeals(meals: List<Meal>) {
        _allMeals.value = meals
    }
    val mealsTypes = listOf("Breakfast", "Lunch", "Dinner", "Snacks")


}