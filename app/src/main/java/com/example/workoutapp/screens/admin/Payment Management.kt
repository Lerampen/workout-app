package com.example.workoutapp.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.data.Payment
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun PaymentManagementScreen(modifier: Modifier = Modifier) {
    Scaffold (topBar = { TopbarPayment() }, content = {paddingValues ->
        Column(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Payments",
                fontSize = 16.sp,
                fontFamily = robotoFontFamily,
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn {

            }
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarPayment(modifier: Modifier = Modifier) {
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
                    text = " Payment Management ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { /*navController.popBackStack()*/ }) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Back arrow"
                )
            } },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}

@Composable
fun PaymentsCard(modifier: Modifier = Modifier, payment: Payment) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
              Text(
                  text = payment.amount.toString(),
                  style = MaterialTheme.typography.bodyMedium,
                  fontSize = 12.sp,
                  fontFamily = robotoFontFamily
              )
                Text(
                    text = payment.paymentMethod,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    fontFamily = robotoFontFamily
                )
                Text(
                    text = payment.timestamp.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    fontFamily = robotoFontFamily
                )
                // TODO: change the schema to store the status of payment and the user who paid it
            }
            IconButton(onClick = { /**onViewDetailsClick**/ }) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "View Details")
            }

        }
    }
}