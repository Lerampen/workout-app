package com.example.workoutapp.repository

import com.example.workoutapp.data.Workout
import com.example.workoutapp.data.WorkoutDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutRepository @Inject constructor(private val workoutDao: WorkoutDao) : WorkoutRepositoryImpl {
    override fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()
    override suspend fun insertWorkout(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    override suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    override suspend fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }
}