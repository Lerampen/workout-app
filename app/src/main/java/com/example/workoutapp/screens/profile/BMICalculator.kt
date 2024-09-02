package com.example.workoutapp.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.workoutapp.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreen(modifier: Modifier = Modifier, navController: NavController ) {
Scaffold (
    topBar = { TopSectionBmi(navController = navController ) },
    content = { paddingValues ->
        Column(modifier = modifier.padding(paddingValues = paddingValues)) {
            BMICalculator (
                onCalculate = {}
            )
        }
    }
)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSectionBmi(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        // TODO:  Text(text = "Workout Screen" , textAlign = TextAlign.Center)
        title = {

            Column(
                modifier = modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ){

                Text(
                    text = " BMI ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "back arrow"
                )
            } },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}
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

            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
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


