package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.workoutapp.navigation.BottomNavigationBar
import com.example.workoutapp.navigation.Screens
import com.example.workoutapp.screens.SignIn
import com.example.workoutapp.screens.SignUp
import com.example.workoutapp.screens.admin.AdminDashBoard
import com.example.workoutapp.screens.admin.ExerciseManagementScreen
import com.example.workoutapp.screens.admin.NutritionManagementScreen
import com.example.workoutapp.screens.admin.PaymentManagementScreen
import com.example.workoutapp.screens.admin.UserManagementScreen
import com.example.workoutapp.screens.admin.WorkoutManagementScreen
import com.example.workoutapp.screens.home.Home
import com.example.workoutapp.screens.nutrition.Nutrition
import com.example.workoutapp.screens.payment.Payment
import com.example.workoutapp.screens.profile.BMICalculator
import com.example.workoutapp.screens.profile.PersonalDetailsScreen
import com.example.workoutapp.screens.profile.Profile
import com.example.workoutapp.screens.workouts.ExerciseDetail
import com.example.workoutapp.screens.workouts.ExerciseList
import com.example.workoutapp.screens.workouts.Workout
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.viewmodels.DashboardViewModel
import com.example.workoutapp.viewmodels.LogInViewModel
import com.example.workoutapp.viewmodels.NutritionManagementViewModel
import com.example.workoutapp.viewmodels.PaymentManagementViewModel
import com.example.workoutapp.viewmodels.PaymentViewModel
import com.example.workoutapp.viewmodels.ProfileViewModel
import com.example.workoutapp.viewmodels.SignUpViewModel
import com.example.workoutapp.viewmodels.UsersMgmtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkoutAppTheme {
                val navController = rememberNavController()
                Scaffold (
                    bottomBar = { BottomNavigationBar(navController = navController)}
                ) { innerPadding ->
                    NavHostContainer(
                        navController = navController,
                        Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}


@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignUp.route,
        modifier = modifier
    ) {
        composable(Screens.SignUp.route){
            val viewModel = hiltViewModel<SignUpViewModel>()

            SignUp (onNavigateToLogin = { navController.navigate(Screens.Login.route) }, signUpViewModel = viewModel )
        }
        composable(Screens.Login.route){
            val viewModel = hiltViewModel<LogInViewModel>()

            SignIn(
                onNavigateToSignUp = { navController.navigate(Screens.SignUp.route) },
                navController = navController,
                logInViewModel = viewModel
            )
        }
        composable(Screens.Home.route) {
            Home(navController = navController)
        }
        composable(Screens.Workouts.route) {
            Workout(navController = navController)
        }
        composable(Screens.Nutrition.route) {
            Nutrition(navController = navController)
        }
        composable(Screens.Payment.route) {
            val viewModel = hiltViewModel<PaymentViewModel>()
            Payment(navController = navController, viewModel = viewModel)
        }
        composable(Screens.Profile.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            Profile(navController = navController, profileViewModel = viewModel)
        }
        composable(Screens.PersonalDetails.route) {
            PersonalDetailsScreen(navController = navController)
        }
        composable(Screens.BMICalculator.route) {
         BMICalculator(onCalculate = {})
        }
        composable(
            route =  Screens.ExerciseList.route ,
            arguments = listOf(navArgument("day") { type = NavType.StringType })
        ) { backStackEntry ->
            val day = backStackEntry.arguments?.getString("day") ?: ""
            ExerciseList(day = day, navController = navController)
        }
        composable(
            route = Screens.ExerciseDetail.route,
            arguments = listOf(navArgument("exerciseId"){type = NavType.StringType})
            ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getInt("exerciseId") ?: 0
            ExerciseDetail(exerciseId = exerciseId)
        }
        composable(route = Screens.AdminDashboard.route,
            ){
            val viewModel = hiltViewModel<DashboardViewModel>()
            AdminDashBoard( navController = navController, dashboardViewModel = viewModel)
        }
        composable(Screens.UserManagement.route){
            val viewModel = hiltViewModel<UsersMgmtViewModel>()
            UserManagementScreen( navController = navController, viewModel = viewModel)
        }
        composable(Screens.WorkoutManagement.route) {
            WorkoutManagementScreen(navController)
        }
        composable(
            route = Screens.ExerciseManagement.route,
            arguments = listOf(navArgument("workoutId"){ type = NavType.IntType })
        ) { backStackEntry ->
            val workoutId =
                backStackEntry.arguments?.getInt("workoutId") ?: return@composable
            ExerciseManagementScreen(
                workoutId = workoutId,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.NutritionManagement.route) {
            val viewModel = hiltViewModel<NutritionManagementViewModel>()
            NutritionManagementScreen(navController,viewModel)
        }
        composable(Screens.PaymentManagement.route) {
            val viewModel = hiltViewModel<PaymentManagementViewModel>()
            PaymentManagementScreen(navController,viewModel)
        }
    }
}