package com.example.workoutapp.screens.workouts

import java.util.UUID

data class Exercise(
    val exerciseName: String,
    val exerciseIllustration: Int,
    val repetitions: Int,
    val id: String = UUID.randomUUID().toString()
) {

}
