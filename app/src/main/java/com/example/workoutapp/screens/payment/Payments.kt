package com.example.workoutapp.screens.payment

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.viewmodels.PaymentViewModel

@Composable
fun Payment(navController: NavController, modifier: Modifier = Modifier, viewModel: PaymentViewModel) {
    var showPaymentSelection by remember { mutableStateOf(false) }

    BackHandler {
        navController.popBackStack()
    }
    Scaffold(
        topBar = {
            TopSectionPay(navController = navController )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showPaymentSelection = !showPaymentSelection },
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Payment")
            }
        },
        content = { paddingValues ->
            PaymentContent(
                modifier = modifier
                    .padding(paddingValues),
                viewModel,
                showPaymentSelection = showPaymentSelection,
                onHidePaymentSelection = {showPaymentSelection =  false}
                )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        val viewModel : PaymentViewModel = hiltViewModel()

        Payment(navController = navController, modifier = Modifier, viewModel =  viewModel )
    }
}

data class Payments(

    val date: String,
    val amount: String,
    val method: String
)
