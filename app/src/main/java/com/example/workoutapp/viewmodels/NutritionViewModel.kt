package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workoutapp.data.AppDatabase
import com.example.workoutapp.model.Meal
import com.example.workoutapp.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NutritionViewModel(application: Application): AndroidViewModel(application = application) {

    private val repository: MealRepository

  private val _allMeals = MutableStateFlow<List<Meal>>(emptyList())
           var allMeals : StateFlow<List<Meal>> = _allMeals
    init {
        val mealDao = AppDatabase.getDatabase(application).mealDao()
        repository = MealRepository(mealDao)

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