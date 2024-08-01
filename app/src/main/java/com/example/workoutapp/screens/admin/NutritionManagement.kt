package com.example.workoutapp.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutapp.R
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.MealTypes
import com.example.workoutapp.data.NutritionPlan
import com.example.workoutapp.data.NutritionPlanWithMeals
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.NutritionManagementViewModel

@Composable
fun NutritionManagementScreen(
    navController: NavController,
    viewModel: NutritionManagementViewModel = hiltViewModel()
) {
    val nutritionPlans by viewModel.nutritionPlans.collectAsState()
    var showAddEditDialog by remember { mutableStateOf(false) }
    var selectedPlan by remember { mutableStateOf<NutritionPlanWithMeals?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopbarNutrManagement(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                selectedPlan = null
                showAddEditDialog = true
            },
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Nutrition Plan" )
            }
        },
        content = { paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange ={
                    searchQuery = it
                    viewModel.searchNutritionPlans(it)
                    
                },
                    label = {
                        Text(text = "Search Nutrition Plans" , fontFamily = robotoFontFamily)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    )
                LazyColumn {
                    items(nutritionPlans){ plan ->
                        NutritionPlanCard(
                        plan = plan,
                        onEditClick = {
                            selectedPlan = plan
                            showAddEditDialog = true
                        },
                        onDeleteClick = {
                            viewModel.deleteNutritionPlan(plan)
                        }
                        )
                        
                    }
                }
            }
            if(showAddEditDialog){
                AddEditNutritionPlanDialog(
                    plan = selectedPlan,
                    onDismiss = { showAddEditDialog = false },
                    onSave = { updatedPlan ->
                        if (selectedPlan == null){
                           viewModel.addNutritionPlan(updatedPlan.plan.name, meals = updatedPlan.meals)
                        } else{
                            viewModel.updateNutritionPlan(updatedPlan)
                        }

                        showAddEditDialog = false
                    }
                    )
            }
        }
    )
}

@Composable
fun NutritionPlanCard(
    plan: NutritionPlanWithMeals,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = plan.plan.name,
                    fontFamily = robotoFontFamily,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    IconButton(
                        onClick = onEditClick,
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Black)
                    ) {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Edit")
                    }
                    IconButton(
                        onClick = onDeleteClick,
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red)
                    ) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
                    }
                }

                plan.meals.forEach { meal ->
                    Text(
                        text = "${meal.type}: ${meal.mealName}",
                        fontFamily = robotoFontFamily,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Calories: ${meal.calories}, Carbs: ${meal.carbs}g, Protein: ${meal.protein}g, Fat: ${meal.fat}",
                        fontFamily = robotoFontFamily,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopbarNutrManagement(navController: NavController) {
        TopAppBar(
            title = {

                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Nutrition Management",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }


            },
            modifier = Modifier,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "Back arrow"
                    )
                }
            },
            actions = {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
            }
        )
    }

@Composable
fun AddEditNutritionPlanDialog(
    plan: NutritionPlanWithMeals?,
    onDismiss: () -> Unit,
    onSave: (NutritionPlanWithMeals) -> Unit) {
    var name by remember {
        mutableStateOf(plan?.plan?.name ?: "")
    }
    var meals by remember {
        mutableStateOf(plan?.meals ?: emptyList())
    }
    
    AlertDialog(
        onDismissRequest = onDismiss, 
        title = { Text(text = if (plan == null) "Add Nutrition Plan" else " Edit Nutrition Plan")},
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Plan Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Meals", fontFamily = robotoFontFamily, style = MaterialTheme.typography.titleMedium)
                meals.forEach { meal ->
                    MealItem(
                        meal = meal,
                        onEdit = { editedMeal ->
                            meals = meals.map { if (it.mealId == editedMeal.mealId) editedMeal else it }
                        },
                        onDelete = {
                            meals = meals.filter { it.mealId != meal.mealId }
                        }
                    )
                }
                Button(onClick = { meals = meals + Meal(
                    mealId = 0, // Room will auto-generate the ID
                    mealName = "",
                    type = MealTypes.BREAKFAST,
                    calories = 0,
                    carbs = 0,
                    protein = 0,
                    fat = 0,
                    planId = plan?.plan?.id ?: 0,
                    timestamp = System.currentTimeMillis(),
                    imageResourceId = R.drawable.pexels_nicola_barts_7936730
                ) },
                    modifier = Modifier.align(Alignment.End)
                ) {

                    Text(text = "Add Meal")
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newPlan = NutritionPlan(
                        id = plan?.plan?.id ?: 0, name = name)

                    onSave(NutritionPlanWithMeals(plan = newPlan, meals = meals))
         }
            ){
                Text(text = "Save", fontFamily = robotoFontFamily)
            }
                        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel", fontFamily = robotoFontFamily, )
            }
        }
        )

}

@Composable
fun MealItem(meal: Meal, onEdit: (Meal) -> Unit, onDelete: () -> Unit) {
    var showEditDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "${meal.type}: ${meal.mealName}")
                Text(
                    text = "Cal: ${meal.calories}, C: ${meal.carbs}g, P: ${meal.protein}g, F: ${meal.fat}g",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Row {
                IconButton(onClick = { showEditDialog = true }) {
                    Icon(Icons.Outlined.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Outlined.Delete, contentDescription = "Delete")
                }
            }
        }
    }

    if (showEditDialog) {
        EditMealDialog(
            meal = meal,
            onDismiss = { showEditDialog = false },
            onSave = {
                onEdit(it)
                showEditDialog = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMealDialog(meal: Meal, onDismiss: () -> Unit, onSave: (Meal) -> Unit) {
    var name by remember { mutableStateOf(meal.mealName) }
    var type by remember { mutableStateOf(meal.type) }
    var calories by remember { mutableStateOf(meal.calories.toString()) }
    var carbs by remember { mutableStateOf(meal.carbs.toString()) }
    var protein by remember { mutableStateOf(meal.protein.toString()) }
    var fat by remember { mutableStateOf(meal.fat.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Meal") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Meal Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = {}
                ) {
                    OutlinedTextField(
                        value = type.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Meal Type") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = false,
                        onDismissRequest = {}
                    ) {
                        MealTypes.values().forEach { mealType ->
                            DropdownMenuItem(
                                text = { Text(mealType.name) },
                                onClick = { type = mealType }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = carbs,
                    onValueChange = { carbs = it },
                    label = { Text("Carbs (g)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = protein,
                    onValueChange = { protein = it },
                    label = { Text("Protein (g)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = fat,
                    onValueChange = { fat = it },
                    label = { Text("Fat (g)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    meal.copy(
                        mealName = name,
                        type = type,
                        calories = calories.toIntOrNull() ?: 0,
                        carbs = carbs.toIntOrNull() ?: 0,
                        protein = protein.toIntOrNull() ?: 0,
                        fat = fat.toIntOrNull() ?: 0
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

