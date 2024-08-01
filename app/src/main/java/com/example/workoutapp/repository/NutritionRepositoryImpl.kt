package com.example.workoutapp.repository

import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.NutritionPlan
import com.example.workoutapp.data.NutritionPlanWithMeals
import kotlinx.coroutines.flow.Flow

interface NutritionRepositoryImpl {
    fun getAllNutritionPlans(): Flow<List<NutritionPlanWithMeals>>
    suspend fun insertNutritionPlan(plan: NutritionPlan, meals: List<Meal>)
    suspend fun updateNutritionPlan(plan: NutritionPlanWithMeals)
    suspend fun deleteNutritionPlan(plan: NutritionPlanWithMeals)
    fun searchNutritionPlans(query: String): Flow<List<NutritionPlanWithMeals>>

}