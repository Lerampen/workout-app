package com.example.workoutapp.screens.workouts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Workout(navController: NavController,modifier: Modifier = Modifier) {
    BackHandler {
        navController.popBackStack()
    }
    Scaffold(
        topBar = { TopSectionWorkout(navController,modifier) }
    ) { paddingValues ->

        Column (modifier = Modifier.padding(paddingValues)){
            HorizontalList()
            ListSection(navController = navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun WorkoutPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        Workout(navController = navController)
    }
}

@Composable
fun TopSectionWorkout(navController : NavController,modifier: Modifier = Modifier) {
    // TODO: top app bar
    TopBarSection(navController = navController)
}

@Composable
fun HorizontalList(modifier: Modifier = Modifier) {
    // TODO: target body workouts in a lazy row
    AfterTopSection()
}

@Composable
fun ListSection(modifier: Modifier = Modifier, navController: NavController) {
    // TODO: Lazy list section
    WorkoutList(modifier = modifier, navController = navController)
}
