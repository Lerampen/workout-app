package com.example.workoutapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("Select * from workouts")
    fun getAllWorkouts(): Flow<List<Workout>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Update
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query(" SELECT * FROM workouts \n" +
            "        WHERE day LIKE '%' || :query || '%' \n" +
            "        OR duration LIKE '%' || :query || '%'\n" +
            "        OR exerciseCount LIKE '%' || :query || '%'")
    fun searchWorkouts(query: String): Flow<List<Workout>>


}