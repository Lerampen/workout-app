package com.example.workoutapp.navigation

sealed class Screens(val route:String) {
    data object Login :Screens("login_screen")
    data object SignUp :Screens("signup_screen")
    data object Home :Screens("home_screen")
    data object Workouts :Screens("workout_screen")
    data object Nutrition :Screens("nutrition_screen")
    data object Payment :Screens("payment_screen")
    data object Profile :Screens("profile_screen")
    data object PersonalDetails : Screens("personal_details")
    data object BMICalculator : Screens("bmi_calculator")
    data  object ExerciseList : Screens("exercise_list/{day}")
    data object ExerciseDetail : Screens("exercise_detail/{exerciseId}")
    data object AdminDashboard : Screens("admin_dashboard")
    data object  UserManagement : Screens("user_management")
    data object  WorkoutManagement : Screens("workout_management")
    data object  NutritionManagement : Screens("nutrition_management")
    data object  PaymentManagement : Screens("payment_management")
    data object ExerciseManagement : Screens("exercise_management/{workoutId}")





}