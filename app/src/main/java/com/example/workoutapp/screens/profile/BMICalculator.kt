package com.example.workoutapp.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun BMICalculator(onCalculate: (Double) -> Unit) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf<Double?>(null) }
    var bmiCategory by remember { mutableStateOf("") }

    val calculateBMI = {
        val heightInMeters = height.toDoubleOrNull()?.div(100)
        val weightInKg = weight.toDoubleOrNull()

        if (heightInMeters != null && weightInKg != null) {
            val calculatedBMI = weightInKg / (heightInMeters * heightInMeters)
            bmiResult = calculatedBMI
            bmiCategory = when {
                calculatedBMI < 18.5 -> "Underweight"
                calculatedBMI in 18.5..24.9 -> "Normal weight"
                calculatedBMI in 25.0..29.9 -> "Overweight"
                else -> "Obesity"
            }
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BMI Calculator",
            fontFamily = robotoFontFamily,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick =  calculateBMI,
//            val heightInMeters = height.toDoubleOrNull()?.div(100)
//            val weightInKg = weight.toDoubleOrNull()
//            if (heightInMeters != null && weightInKg != null) {
//                bmiResult = weightInKg / (heightInMeters * heightInMeters)
//                onCalculate(bmiResult!!)
//            }

            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text ="Calculate",
                fontFamily = robotoFontFamily,
            )
        }

        bmiResult?.let {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your BMI: %.2f".format(it),
                fontFamily = robotoFontFamily,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 16.dp)

                )
            Text(
                text = "Category: $bmiCategory",
                fontFamily = robotoFontFamily,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun PersonalDetailsScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Your existing personal details UI

        Button(onClick = { navController.navigate("bmi_calculator") }) {
            Text("Calculate BMI")
        }
    }
}
