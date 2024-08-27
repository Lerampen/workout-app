package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.AppDatabase
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.MealTypes
import com.example.workoutapp.data.NutritionPlan
import com.example.workoutapp.data.NutritionPlanWithMeals
import com.example.workoutapp.repository.MealRepository
import com.example.workoutapp.repository.NutritionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    application: Application ,
    private val repository: MealRepository,
    private val nutritionRepository: NutritionRepository

): AndroidViewModel(application = application) {


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val nutritionPlans: StateFlow<List<NutritionPlanWithMeals>> = searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                nutritionRepository.getAllNutritionPlans()
            } else {
                nutritionRepository.searchNutritionPlans(query)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    private val _allMeals = MutableStateFlow<List<Meal>>(emptyList())
           var allMeals : StateFlow<List<Meal>> = _allMeals
    init {

        viewModelScope.launch{
            repository.getAllMeals().collect{ meals->
                _allMeals.value = meals
            }
        }
    }
    private val _suggestedMeals = MutableStateFlow<List<Meal>>(emptyList())
    val suggestedMeals: StateFlow<List<Meal>> = _suggestedMeals.asStateFlow()

    // Function to get suggested meals
    fun getSuggestedMeals(){
        viewModelScope.launch{

            // You can implement your suggestion logic here
            // For now, we'll just return all meals as suggestions
            repository.getAllMeals().collect { meals ->
                _suggestedMeals.value = meals
            }
        } // Example implementation
    }

    // Function to get suggested meals by type
    fun getSuggestedMealsByType(type: MealTypes): Flow<List<Meal>> {
        return repository.getSuggestedMealsByType(type)
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
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


    fun insertNutritionPlan(plan: NutritionPlan, meals: List<Meal>) {
        viewModelScope.launch {
            nutritionRepository.insertNutritionPlan(plan, meals)
        }
    }

    fun updateNutritionPlan(plan: NutritionPlanWithMeals) {
        viewModelScope.launch {
            nutritionRepository.updateNutritionPlan(plan)
        }
    }

    fun deleteNutritionPlan(plan: NutritionPlanWithMeals) {
        viewModelScope.launch {
            nutritionRepository.deleteNutritionPlan(plan)
        }
    }
}

