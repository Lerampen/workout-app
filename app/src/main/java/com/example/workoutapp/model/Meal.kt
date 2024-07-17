package com.example.workoutapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val mealId: Int = 0,
    @ColumnInfo(name = "type")
    val mealType: String,
    @ColumnInfo(name = "name")
    val mealName: String,
    @ColumnInfo(name = "calories")
    val calories: Int,
    val timestamp: Long,
    @ColumnInfo(name = "image")
    val imageResourceId: Int // Example: Resource ID for drawable image
    // Other fields as needed
)
