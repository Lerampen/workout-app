package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.Workout
import com.example.workoutapp.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WorkoutManagementViewModel @Inject constructor(
    application: Application,
    private val repository: WorkoutRepository
) : AndroidViewModel(application = application ) {

    val workouts : StateFlow<List<Workout>> = repository.getAllWorkouts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addWorkout(workout: Workout){
        viewModelScope.launch {
            repository.insertWorkout(workout)
        }
    }

    fun updateWorkout(workout: Workout){
        viewModelScope.launch {
            repository.updateWorkout(workout)
        }
    }

    fun deleteWorkout(workout: Workout){
        viewModelScope.launch {
            repository.deleteWorkout(workout)
        }
    }
}