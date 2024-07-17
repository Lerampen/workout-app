package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.navigation.BottomNavigationBar
import com.example.workoutapp.navigation.Screens
import com.example.workoutapp.screens.SignIn
import com.example.workoutapp.screens.home.Home
import com.example.workoutapp.screens.nutrition.Nutrition
import com.example.workoutapp.screens.payment.Payment
import com.example.workoutapp.screens.profile.Profile
import com.example.workoutapp.screens.workouts.Workout
import com.example.workoutapp.ui.theme.WorkoutAppTheme
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
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
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
            Payment(navController = navController)
        }
        composable(Screens.Profile.route) {
            Profile(navController = navController)
        }
    }
}