package com.example.workoutapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    var mealId: Int = 0,
    @ColumnInfo(name = "type")
    var type: MealTypes,
    @ColumnInfo(name = "name")
    var mealName: String,
    @ColumnInfo(name = "calories")
    var calories: Int,
    @ColumnInfo(name = "carbs")
    val carbs: Int,
    @ColumnInfo(name = "protein")
    val protein: Int,
    @ColumnInfo(name = "fat")
    val fat: Int,
    @ColumnInfo(name = "plan_id")
    val planId: Int,
    var timestamp: Long,
    @ColumnInfo(name = "image")
    var imageResourceId: Int // Example: Resource ID for drawable image
    // Other fields as needed
)

@Entity(tableName = "nutrition_plans")
data class NutritionPlan(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "name")
    val name : String
)

enum class MealTypes{
    BREAKFAST, LUNCH, DINNER, SNACK
}
