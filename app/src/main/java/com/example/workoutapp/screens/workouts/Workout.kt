package com.example.workoutapp.screens.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Workout(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopBarSection() }
    ) { paddingValues ->

        Column (modifier = Modifier.padding(paddingValues)){
            HorizontalList()
            ListSection()
        }
    }
   TopSectionWorkout()

}

@Preview(showBackground = true)
@Composable
private fun WorkoutPreview() {
    WorkoutAppTheme {
        Workout()
    }
}

@Composable
fun TopSectionWorkout(modifier: Modifier = Modifier) {
    // TODO: top app bar
    TopBarSection()
}

@Composable
fun HorizontalList(modifier: Modifier = Modifier) {
    // TODO: target body workouts in a lazy row
    AfterTopSection()
}

@Composable
fun ListSection(modifier: Modifier = Modifier) {
    // TODO: Lazy list section
    WorkoutList()
}
