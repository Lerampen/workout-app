package com.example.workoutapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Home(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {

        TopPart()
        ActivityStats()
        WorkoutsView()
        MealsView()
    }
}
@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    WorkoutAppTheme {
        Home()
    }
}

@Composable
fun TopPart(modifier: Modifier = Modifier) {

//    therefore the top app bar
    TopSection()

}

@Composable
fun ActivityStats(modifier: Modifier = Modifier) {

    StatsSection()
}

@Composable
fun WorkoutsView(modifier: Modifier = Modifier) {

    WorkoutSection()
}

@Composable
fun MealsView(modifier: Modifier = Modifier) {

    MealsSection()
}

