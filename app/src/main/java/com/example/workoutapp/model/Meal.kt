package com.example.workoutapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    var mealId: Int = 0,
    @ColumnInfo(name = "type")
    var mealType: String,
    @ColumnInfo(name = "name")
    var mealName: String,
    @ColumnInfo(name = "calories")
    var calories: Int,
    var timestamp: Long,
    @ColumnInfo(name = "image")
    var imageResourceId: Int // Example: Resource ID for drawable image
    // Other fields as needed
)
