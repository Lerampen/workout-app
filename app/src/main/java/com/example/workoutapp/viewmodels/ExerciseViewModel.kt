package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.repository.ExerciseRepository
import com.example.workoutapp.data.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    application: Application,
    private val exerciseRepository : ExerciseRepository
) : AndroidViewModel(application = application) {
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    private val _exercise = MutableStateFlow<Exercise?>(null)
    val exercise: StateFlow<Exercise?> = _exercise.asStateFlow()

    fun fetchExercisesForWorkout(workoutId: Int) {
        viewModelScope.launch {
            exerciseRepository.getExercisesForWorkout(workoutId).collect { exercises ->
                _exercises.value = exercises
            }
        }
    }
    fun fetchExerciseById(exerciseId: String) {
        viewModelScope.launch {
            val exercise = exerciseRepository.getExerciseById(exerciseId)
            _exercise.value = exercise
        }
    }


}