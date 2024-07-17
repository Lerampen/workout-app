package com.example.workoutapp.repository

import com.example.workoutapp.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepositoryImpl {
    fun getAllMeals(): Flow<List<Meal>>
    suspend fun insertMeal(meal: Meal)
    suspend fun deleteMeal(meal: Meal)
    suspend fun updateMeal(meal: Meal)
}