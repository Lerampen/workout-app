package com.example.workoutapp.data

import androidx.room.Embedded
import androidx.room.Relation
//Create a data class to represent the relationship between NutritionPlan and Meals:
data class NutritionPlanWithMeals(@Embedded val plan : NutritionPlan,
    @Relation(
        parentColumn = "id",
        entityColumn = "plan_id"
    )
    val  meals : List<Meal>
    )
