package com.example.workoutapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.repository.ExerciseRepository
import com.example.workoutapp.data.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _exerciseState = MutableStateFlow<ExerciseState>(ExerciseState.Loading)
    val exerciseState: StateFlow<ExerciseState> = _exerciseState.asStateFlow()

    fun fetchExercisesForWorkout(workoutId: Int) {
        viewModelScope.launch {
            exerciseRepository.getExercisesForWorkout(workoutId).collect { exercises ->
                _exercises.value = exercises
            }
        }
    }
    fun fetchExerciseById(exerciseId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.getExerciseById(exerciseId).collect { exercise ->
                withContext(Dispatchers.Main) {
                    _exercise.value = exercise
                }
            }
        }
    }
    fun fetchExercisesForDay(day: String) {
        viewModelScope.launch {
            // Fetch exercises for the given day from your data source
            exerciseRepository.getExercisesByDay(day).collect { exercises ->
                // Update _exercises with the fetched data
                _exercises.value = exercises
                Log.d("ExerciseViewModel", "Exercises loaded: ")

            }
        }
    }


}
sealed class ExerciseState {
    data object Loading : ExerciseState()
    data class Success(val exercise: Exercise) : ExerciseState()
    data class Error(val message: String) : ExerciseState()
}