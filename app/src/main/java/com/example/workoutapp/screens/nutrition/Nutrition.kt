package com.example.workoutapp.screens.nutrition

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.R
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.MealTypes
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
    val suggestedMeals by viewModel.suggestedMeals.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSuggestedMeals()
    }
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

//        CalorieCard()
        MealsSectionNutrition()
        // Display suggested meals
        Text(
            text = "Suggested Meals",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(suggestedMeals) { meal ->
                SuggestedMealCard(
                    meal = meal,
                    onClick = {
                        // Handle click on suggested meal
                        viewModel.insert(meal)
                    }
                )
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            val selectedMealType = mealsTypes.value  // Accessing the List<String> inside MutableState

            items(meals.filter {  meal ->
                selectedMealType.contains(meal.type.name) }){ meal ->
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

@Composable
fun SuggestedMealCard(meal: Meal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable(onClick = onClick)
        .width(150.dp),  // Increased width for better visibility
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .width(120.dp)
        ) {
            Text(text = meal.mealName, style = MaterialTheme.typography.bodyMedium,  maxLines = 1)
            Text(text = "${meal.calories} calories", style = MaterialTheme.typography.bodySmall)
            Text(text = meal.type.name, style = MaterialTheme.typography.bodySmall)

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NutritionPreview() {
    WorkoutAppTheme {
        val sampleMeals = listOf(
            sampleMeal(),
            sampleMeal().copy(type = MealTypes.LUNCH, mealName = "Salad", calories = 250, imageResourceId = R.drawable.pexels_nicola_barts_7936730),
            sampleMeal().copy(type = MealTypes.DINNER, mealName = "Ramen", calories = 500, imageResourceId = R.drawable.pexels_nicola_barts_7936744)
        )
        val viewModel = hiltViewModel<NutritionViewModel>().apply {
            setSampleMeals(sampleMeals)
        }
        Nutrition(navController = rememberNavController(), viewModel = viewModel)
    }
}

// TODO: Lazy column Showing the cards of the users'daily meals e.g Breakfast , lunch and dinner or snacks they have consumed  
// TODO: Include a section for the user to add a meal E.g. Add meal + 