package com.example.workoutapp.screens.payment

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Payment(navController: NavController, modifier: Modifier = Modifier) {
    BackHandler {
        navController.popBackStack()
    }
    Scaffold(
        topBar = {
            TopSectionPay(navController = navController )
        },
        content = { paddingValues ->
            PaymentContent(modifier = modifier.padding(paddingValues).verticalScroll(
                rememberScrollState()
            ))
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()

        Payment(navController = navController)
    }
}

data class Payment (

    val date: String,
    val amount: String,
    val method: String
)
