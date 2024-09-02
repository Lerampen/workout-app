package com.example.workoutapp.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.GoalsViewModel

@Composable
fun PersonalDetailsScreen(
    navController: NavController,
    goalsViewModel: GoalsViewModel = viewModel() // Use the same ViewModel instance

) {
    var waterIntake by remember {
        mutableStateOf(goalsViewModel.waterGoal.toString())
    }
    var calorieIntake by remember {
        mutableStateOf("")
    }
    var stepsCount by remember {
        mutableStateOf(goalsViewModel.stepGoal.toString())
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Your existing personal details UI
//        Set section to set water intake goal
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(16.dp)){
                Text("Set Water Intake Goal (ml)", fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = waterIntake, // replace with a state variable
                    onValueChange = {newIntake -> waterIntake = newIntake },
                    placeholder = {
                        Text(
                            text = "Enter goal",
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Button(onClick = {
                    goalsViewModel.updateWaterGoal(waterIntake.toIntOrNull() ?: goalsViewModel.waterGoal)
                }) {
                    Text("Save Water Goal")
                }
            }
        }
        // Section to set steps count goal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Set Steps Count Goal")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = stepsCount, // replace with a state variable
                    onValueChange = { /* handle value change */ newSteps -> stepsCount = newSteps},
                    placeholder = { Text(
                        "Enter goal",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Normal
                    ) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Button(onClick = {
                    goalsViewModel.updateStepGoal(stepsCount.toIntOrNull() ?: goalsViewModel.stepGoal)
                }) {
                    Text("Save Step Goal")
                }
            }
        }
        //        Set section to set calorie intake goal
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(16.dp)){
                Text("Set Calorie Intake Goal (kcal)", fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = calorieIntake, // replace with a state variable
                    onValueChange = {newIntake -> calorieIntake = newIntake },
                    placeholder = {
                        Text(
                            text = "Enter goal",
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }
        }

        // Option to navigate to BMI calculator
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { navController.navigate("bmi_calculator") },
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Calculate BMI", style = MaterialTheme.typography.labelMedium)
                Text("Tap to calculate your Body Mass Index.")
            }
        }
    }

//        Button(onClick = { navController.navigate("bmi_calculator") }) {
//            Text("Calculate BMI")
//        }
    }
