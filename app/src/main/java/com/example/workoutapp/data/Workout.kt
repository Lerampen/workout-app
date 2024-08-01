package com.example.workoutapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day : String,
    val duration : Int,
    val exerciseCount : Int,
    val imageUrl : String
) {
}