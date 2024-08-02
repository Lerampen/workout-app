package com.example.workoutapp.repository

import com.example.workoutapp.data.NutritionPlanWithMeals
import com.example.workoutapp.data.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepositoryImpl {
    fun getAllWorkouts() : Flow<List<Workout>>
    suspend fun insertWorkout(workout: Workout)
    suspend fun  updateWorkout(workout: Workout)
    suspend fun  deleteWorkout(workout: Workout)
    fun searchNutritionPlans(query: String): Flow<List<Workout>>

}