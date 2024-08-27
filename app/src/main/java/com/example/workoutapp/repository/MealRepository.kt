package com.example.workoutapp.repository

import com.example.workoutapp.data.MealDao
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.MealTypes
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class MealRepository @Inject constructor(private  val mealDao: MealDao) : MealRepositoryImpl {
    override fun getAllMeals(): Flow<List<Meal>> {
      return  mealDao.getAllMeals()
    }

    override suspend fun insertMeal(meal: Meal) {
        return mealDao.insertMeal(meal)
    }

    fun getSuggestedMealsByType(type: MealTypes): Flow<List<Meal>> {
        return mealDao.getMealsByType(type)  // Collecting the flow to get the list
    }


    override suspend fun deleteMeal(meal: Meal) {
        return mealDao.deleteMeal(meal)
    }

    override suspend fun updateMeal(meal: Meal) {
        return mealDao.updateMeal(meal)
    }

}