package com.example.workoutapp.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import com.example.workoutapp.screens.payment.PaymentContent
import com.example.workoutapp.screens.payment.TopSectionPay
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Home(navController: NavController, modifier: Modifier = Modifier) {

    BackHandler {
        navController.popBackStack()
    }
    Scaffold(
        topBar = {
            TopPart(navController = navController)

        },
        content = { paddingValues ->
            HomeMerged(modifier = modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()))
        }
    )
}
@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        Home(navController = navController)
    }
}

@Composable
fun HomeMerged(modifier: Modifier = Modifier) {
        Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {

        ActivityStats()
        WorkoutsView()
        MealsView()
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeMergedPreview() {
    WorkoutAppTheme {
        HomeMerged()
    }
}
@Composable
fun TopPart(navController: NavController, modifier: Modifier = Modifier) {

//    therefore the top app bar
    TopSection(navController = navController)

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

