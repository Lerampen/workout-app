package com.example.workoutapp.navigation

sealed class Screens(val route:String) {
    data object Home :Screens("home_screen")
    data object Workouts :Screens("workout_screen")
    data object Nutrition :Screens("nutrition_screen")
    data object Payment :Screens("payment_screen")
    data object Profile :Screens("profile_screen")

}