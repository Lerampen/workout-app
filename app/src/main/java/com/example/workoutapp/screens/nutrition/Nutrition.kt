package com.example.workoutapp.screens.nutrition

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.R
import com.example.workoutapp.data.Meal
import com.example.workoutapp.screens.home.MealsCard
import com.example.workoutapp.screens.home.sampleMeal
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.viewmodels.NutritionViewModel

@Composable
fun Nutrition(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NutritionViewModel = hiltViewModel()
) {
    val meals by viewModel.allMeals.collectAsState()

    val mealsTypes = remember {
        mutableStateOf(listOf("Breakfast","Lunch","Dinner","Snacks"))
    }
    // State to control visibility of the popup form
    var showDialog by remember { mutableStateOf(false) }

//    Function to handle adding a new meal
    val addMeal : (Meal) -> Unit = { newMeal ->
       viewModel.insert(newMeal)
    }
    //navigation for back button
    BackHandler {
        navController.popBackStack()
    }

    Scaffold (
        topBar =  { TopSectionNutr(navController = navController) },

    ){paddingValues ->

    Column (
        modifier = modifier.padding(paddingValues)
     ){

        CalorieCard()
        MealsSectionNutrition()
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            val selectedMealType = mealsTypes.value  // Accessing the List<String> inside MutableState

            items(meals.filter {  meal ->
                selectedMealType.contains(meal.mealType) }){ meal ->
                MealsCard(meal = meal)
            }
            item {
                AddMealButton(onClick = {
                     showDialog = true
                })
            }
        }
    }
        // Show the add meal popup form when showDialog is true
        if (showDialog) {
            AddMealPopup(
                onAddMeal = { newMeal ->
                    addMeal(newMeal)
                    showDialog = false
                },
                onDismiss = { showDialog = false },
                mealsTypes = mealsTypes.value
            )
        }

    }
}



@Preview(showBackground = true)
@Composable
private fun NutritionPreview() {
    WorkoutAppTheme {
        val sampleMeals = listOf(
            sampleMeal(),
            sampleMeal().copy(mealType = "Lunch", mealName = "Salad", calories = 250, imageResourceId = R.drawable.pexels_nicola_barts_7936730),
            sampleMeal().copy(mealType = "Dinner", mealName = "Ramen", calories = 500, imageResourceId = R.drawable.pexels_nicola_barts_7936744)
        )
        val viewModel = hiltViewModel<NutritionViewModel>().apply {
            setSampleMeals(sampleMeals)
        }
        Nutrition(navController = rememberNavController(), viewModel = viewModel)
    }
}

// TODO: Lazy column Showing the cards of the users'daily meals e.g Breakfast , lunch and dinner or snacks they have consumed  
// TODO: Include a section for the user to add a meal E.g. Add meal + 