package com.example.workoutapp.repository

import com.example.workoutapp.data.NutritionDao
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.NutritionPlan
import com.example.workoutapp.data.NutritionPlanWithMeals
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NutritionRepository @Inject constructor(
    private val nutritionDao: NutritionDao
): NutritionRepositoryImpl {
    override fun getAllNutritionPlans(): Flow<List<NutritionPlanWithMeals>>
    = nutritionDao.getAllNutritionPlansWithMeals()

    override suspend fun insertNutritionPlan(plan: NutritionPlan, meals: List<Meal>) {
        val planId = nutritionDao.insertNutritionPlan(plan)
        meals.forEach{ meal ->
            nutritionDao.insertMeal(meal.copy(planId = planId.toInt()))
        }
    }

    override suspend fun updateNutritionPlan(plan: NutritionPlanWithMeals) {
       nutritionDao.updateNutritionPlan(plan.plan)
        plan.meals.forEach { meal ->
            nutritionDao.updateMeal(meal)
        }
    }

    override suspend fun deleteNutritionPlan(plan: NutritionPlanWithMeals) {
        nutritionDao.deleteNutritionPlan(plan.plan)
        nutritionDao.deleteMealsForPlan(plan.plan.id)
    }

    override fun searchNutritionPlans(query: String): Flow<List<NutritionPlanWithMeals>> =
        nutritionDao.searchNutritionPlans(query)

}