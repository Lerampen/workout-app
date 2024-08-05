package com.example.workoutapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val exerciseName: String,
    val exerciseIllustration: String,
    val workoutId :  Int, // Reference to the Workout
    val repetitions: Int,
) {

}
