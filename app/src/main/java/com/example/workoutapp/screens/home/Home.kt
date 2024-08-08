package com.example.workoutapp.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.data.User
import com.example.workoutapp.screens.payment.PaymentContent
import com.example.workoutapp.screens.payment.TopSectionPay
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.viewmodels.UsersMgmtViewModel

@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsersMgmtViewModel = hiltViewModel()
) {
    val currentUser by viewModel.currentUser.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCurrentUser()
    }
    BackHandler {
        navController.popBackStack()
    }
    Scaffold(
        topBar = {
            TopPart(navController = navController, currentUser = currentUser)

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
fun TopPart(navController: NavController, currentUser : User?) {

//    therefore the top app bar
    TopSection(navController = navController, currentUser =  currentUser)

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

