package com.example.workoutapp.repository

import com.example.workoutapp.data.ExerciseDao
import com.example.workoutapp.data.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    fun getExercisesForWorkout(workoutId: Int): Flow<List<Exercise>> {
        return exerciseDao.getExercisesForWorkout(workoutId)
    }
    fun getExerciseById(exerciseId: String): Exercise {
        val exerciseEntity = exerciseDao.getExerciseById(exerciseId)
        return Exercise(
            id = exerciseEntity.id,
            exerciseName = exerciseEntity.exerciseName,
            exerciseIllustration = exerciseEntity.exerciseIllustration,
            workoutId = exerciseEntity.workoutId,
            repetitions = exerciseEntity.repetitions
        )
    }

    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }
}