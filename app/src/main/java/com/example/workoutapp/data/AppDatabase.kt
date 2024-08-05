package com.example.workoutapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities =
[
    Meal::class,
    Payment::class,
    Workout::class,
    Exercise::class,
    NutritionPlan::class],
    version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun paymentDao(): PaymentDao
    abstract fun  workoutDao(): WorkoutDao
    abstract fun nutritionDao(): NutritionDao
    abstract fun exerciseDao(): ExerciseDao


}