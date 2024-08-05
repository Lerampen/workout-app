package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.Workout
import com.example.workoutapp.repository.ExerciseRepository
import com.example.workoutapp.repository.WorkoutRepository
import com.example.workoutapp.data.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WorkoutManagementViewModel @Inject constructor(
    application: Application,
    private val repository: WorkoutRepository,
    private val exerciseRepository: ExerciseRepository

) : AndroidViewModel(application = application ) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    val workouts : StateFlow<List<Workout>> = combine(
        repository.getAllWorkouts(),
        searchQuery
    ){ workouts, query ->
        if (query.isBlank()){
            workouts
        }else{
            workouts.filter { workout ->
                workout.day.contains(query, ignoreCase = true) ||
                        workout.duration.toString().contains(query, ignoreCase = true) ||
                        workout.exerciseCount.toString().contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
//    another alternative
//    val workouts: StateFlow<List<Workout>> = searchQuery
//        .flatMapLatest { query ->
//            if (query.isBlank()) {
//                repository.getAllWorkouts()
//            } else {
//                repository.searchWorkouts(query)
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

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
    fun searchWorkouts(query : String){
        _searchQuery.value = query
    }

    fun fetchExercisesForWorkout(workoutId: Int) {
        viewModelScope.launch {
            exerciseRepository.getExercisesForWorkout(workoutId).collect {
                _exercises.value = it
            }
        }
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.addExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.updateExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.deleteExercise(exercise)
        }
    }
}