package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.NutritionPlan
import com.example.workoutapp.data.NutritionPlanWithMeals
import com.example.workoutapp.repository.NutritionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionManagementViewModel @Inject constructor(
    application: Application,
    private val repository : NutritionRepository
) : AndroidViewModel(application = application){
    private val _nutritionPlans = MutableStateFlow<List<NutritionPlanWithMeals>>(emptyList())
    val nutritionPlans  : StateFlow<List<NutritionPlanWithMeals>> = _nutritionPlans

    init {
        viewModelScope.launch{
            repository.getAllNutritionPlans().collect{
                _nutritionPlans.value = it
            }
        }
    }
    fun addNutritionPlan(name : String, meals: List<Meal>){
        viewModelScope.launch {
            val newPlan = NutritionPlan(name = name)
            repository.insertNutritionPlan(newPlan, meals)
        }
    }
     fun updateNutritionPlan(planWithMeals: NutritionPlanWithMeals){
         viewModelScope.launch {
             repository.updateNutritionPlan(planWithMeals)
         }
     }

    fun deleteNutritionPlan(planWithMeals: NutritionPlanWithMeals){
        viewModelScope.launch {
            repository.deleteNutritionPlan(planWithMeals)
        }
    }
     fun searchNutritionPlans(query : String){
         viewModelScope.launch {
             repository.searchNutritionPlans(query = query).collect{
                 _nutritionPlans.value = it
             }
         }
     }
}