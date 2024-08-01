package com.example.workoutapp.screens.nutrition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.workoutapp.R
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.MealTypes
import com.example.workoutapp.ui.theme.robotoFontFamily

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
                    label = { Text(text = "Meal Name", fontFamily = robotoFontFamily) } ,
                    placeholder = { Text(text = " Enter meal name", fontFamily = robotoFontFamily ) },
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
                            // TODO: debug 
                            val newMeal = Meal(
                                type =  MealTypes.BREAKFAST,
                                mealName = mealName,
                                calories = calories,
                                timestamp = System.currentTimeMillis(),
                                imageResourceId = R.drawable.pexels_nicola_barts_7936744,
                                planId = 2 ,
                                carbs = 120 ,
                                protein = 80,
                                fat = 100
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