package com.example.workoutapp.screens.payment

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Payment(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopSectionPay()
        },
        content = { paddingValues ->
            PaymentContent(modifier = modifier.padding(paddingValues))
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentPreview() {
    WorkoutAppTheme {
        Payment()
    }
}

data class Payment (

    val date: String,
    val amount: String,
    val method: String
)