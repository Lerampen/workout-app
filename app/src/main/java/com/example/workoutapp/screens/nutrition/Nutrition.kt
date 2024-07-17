package com.example.workoutapp.screens.nutrition

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.screens.home.MealsCard
import com.example.workoutapp.screens.workouts.HorizontalList
import com.example.workoutapp.screens.workouts.ListSection
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Nutrition(navController: NavController,modifier: Modifier = Modifier) {

    val meals = remember {
        mutableStateOf(listOf("Breakfast","Lunch","Dinner","Snacks"))
    }
    //navigation for back button
    BackHandler {
        navController.popBackStack()
    }

    Scaffold (
        topBar =  { TopSectionNutr(navController = navController) },

    ){paddingValues ->

    Column (modifier = modifier.padding(paddingValues)){

        CalorieCard()
        MealsSectionNutrition()
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(meals.value){meal ->
                MealsCard(meal = meal)
            }
            item {
                AddMealButton(onClick = { /*TODO: Handle meal action*/ })
            }
        }
    }

    }
}

@Preview(showBackground = true)
@Composable
private fun NutritionPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        Nutrition(navController = navController)
    }
}

// TODO: Lazy column Showing the cards of the users'daily meals e.g Breakfast , lunch and dinner or snacks they have consumed  
// TODO: Include a section for the user to add a meal E.g. Add meal + 