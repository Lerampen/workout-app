package com.example.workoutapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GoalsViewModel : ViewModel() {
    var waterGoal by mutableIntStateOf(2000) // Default to 2000 ml
        private set

    var stepGoal by mutableIntStateOf(10000) // Default to 10000 steps
        private set

    fun updateWaterGoal(newGoal: Int) {
        waterGoal = newGoal
    }

    fun updateStepGoal(newGoal: Int) {
        stepGoal = newGoal
    }
}