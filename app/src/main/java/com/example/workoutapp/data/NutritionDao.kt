package com.example.workoutapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface NutritionDao {
    @Transaction
    @Query("SELECT * FROM nutrition_plans")
    fun getAllNutritionPlansWithMeals(): Flow<List<NutritionPlanWithMeals>>
    @Insert
    suspend fun insertNutritionPlan(plan: NutritionPlan):Long
    @Insert
    suspend fun insertMeal(meal: Meal)
    @Update
    suspend fun updateNutritionPlan(plan: NutritionPlan)
    @Update
    suspend fun updateMeal(meal: Meal)
    @Delete
    suspend fun deleteNutritionPlan(plan: NutritionPlan)
    @Delete
    suspend fun deleteMeal(meal: Meal)
    @Query("DELETE FROM meals WHERE plan_id = :planId")
    suspend fun deleteMealsForPlan(planId : Int)

    @Transaction
    @Query("SELECT * FROM nutrition_plans WHERE name LIKE '%' || :query || '%'")
    fun searchNutritionPlans(query : String) : Flow<List<NutritionPlanWithMeals>>

}