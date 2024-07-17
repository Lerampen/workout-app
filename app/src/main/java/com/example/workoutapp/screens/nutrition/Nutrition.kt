package com.example.workoutapp.screens.nutrition

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.R
import com.example.workoutapp.model.Meal
import com.example.workoutapp.screens.home.MealsCard
import com.example.workoutapp.screens.home.sampleMeal
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealPopup(
    onAddMeal: (meal: Meal) -> Unit,
    onDismiss: () -> Unit,
    mealsTypes: List<String>  // Pass mealsTypes as a parameter
     ) {
    // Local state to hold meal details
    var mealName
    by
    remember { mutableStateOf("") }
    var mealType by remember { mutableStateOf(mealsTypes.first()) }
    var calories by remember { mutableIntStateOf(0) }
    var expand by remember { mutableStateOf(false) }

    // Function to clear input fields and dismiss the dialog
    fun clearAndDismiss() {
        mealName = ""
        mealType = mealsTypes.first()
        calories = 0
        onDismiss()
    }
    Dialog(
        onDismissRequest = { clearAndDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp
            ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Add nem meal", fontFamily = robotoFontFamily, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                
//                Meal Name Input
                OutlinedTextField(
                    value = mealName,
                    onValueChange = {mealName = it},
                    label = { Text(text = "Meal Name", fontFamily = robotoFontFamily)} ,
                    placeholder = { Text(text = " Enter meal name", fontFamily = robotoFontFamily )},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Meal Type Dropdown
//                DropdownMenu(
//                    expanded = false,
//                    onDismissRequest = { },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    mealsTypes.forEach { type ->
//                        DropdownMenuItem( text = { Text(text = type) }, onClick = { mealType = type })
//                    }
//                }
                ExposedDropdownMenuBox(
                    expanded = expand,
                    onExpandedChange = { expand = it },
                ) {
                    OutlinedTextField(
                        value = mealType,
                        onValueChange = { },
                        label = { Text(text = "Meal Type", fontFamily = robotoFontFamily) },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expand)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expand,
                        onDismissRequest = { expand = false }
                    ) {
                        mealsTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(text = type) },
                                onClick = {
                                    mealType = type
                                    expand = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Calories Input
                OutlinedTextField(
                    value = calories.toString(),
                    onValueChange = { calories = it.toIntOrNull() ?: 0 },
                    label = { Text("Calories") },
                    placeholder = { Text(text = "Enter calories") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { clearAndDismiss() }) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(
                        onClick = {
                            // Create the meal object
                            val newMeal = Meal(
                                mealType = mealType,
                                mealName = mealName,
                                calories = calories,
                                timestamp = System.currentTimeMillis(),
                                imageResourceId = R.drawable.pexels_nicola_barts_7936744
                            )

                            // Pass the meal object to the callback function
                            onAddMeal(newMeal)
                            clearAndDismiss()
                        }
                    ) {
                        Text("Add")
                    }
                }
            }
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