package com.example.workoutapp.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.screens.home.MealsCard
import com.example.workoutapp.screens.nutrition.AddMealButton
import com.example.workoutapp.screens.nutrition.CalorieCard
import com.example.workoutapp.screens.nutrition.MealsSectionNutrition
import com.example.workoutapp.screens.nutrition.TopSectionNutr
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Profile(navController: NavController, modifier: Modifier = Modifier) {
    BackHandler {
        navController.popBackStack()
    }
    Scaffold (
        topBar =  { TopSectionProf(navController = navController) },

        ){ paddingValues ->

        Column (
            modifier = modifier
            .padding(paddingValues)
            ){
            ProfileImage()
            MidSection()
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        Profile(navController = navController)
    }
}