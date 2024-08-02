package com.example.workoutapp.screens.admin

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutapp.data.Payment
import com.example.workoutapp.data.Workout
import com.example.workoutapp.screens.payment.PaymentMethodsSelection
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.NutritionManagementViewModel
import com.example.workoutapp.viewmodels.PaymentManagementViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PaymentManagementScreen(
    navController: NavController,
    viewModel: PaymentManagementViewModel = hiltViewModel()
) {

    BackHandler {
        navController.popBackStack()
    }

    val payments by viewModel.filteredPayments.collectAsState()
    var showAddEditDialog by remember {
        mutableStateOf(false)
    }
    var selectedPayment by remember {
        mutableStateOf<Payment?>(null)
    }
    var searchQuery by remember { mutableStateOf("") }
    var showPaymentDetails by remember { mutableStateOf(false) }





    Scaffold (
        topBar = { TopbarPayment(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedPayment = null
                showAddEditDialog  =  true
            },
                containerColor = Color.Black,
                contentColor = Color.White

            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Payment")

        }},
        content = {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchPayments(it)
                },
                label = {
                    Text(text = "Search Payments", fontFamily = robotoFontFamily )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ){
                items(payments){ payment ->
                    PaymentCard(
                        payment = payment,
                        onEditClick = {
                            selectedPayment = payment
                            showPaymentDetails = true
                        },
                        onDeleteClick = {
                            viewModel.delete(payment)
                        },
                        onViewClick = {
                            selectedPayment = payment
                            showPaymentDetails = true
                        }
                    )
                }
            }
        }
            if (showAddEditDialog){
                AddEditPaymentDialog(
                    payment = selectedPayment,
                    onDismiss = { showAddEditDialog = false },
                    onSave = { updatedPayment ->
                        if (selectedPayment == null){
                            viewModel.insert(updatedPayment)
                        } else{
                            viewModel.update(updatedPayment)
                        }
                        showAddEditDialog = false

                    }
                )
            }
            if (showPaymentDetails){
                PaymentDetailDialog(
                    payment = selectedPayment,
                    onDismiss = { showPaymentDetails = false}

                )
            }
    })
}



@Composable
fun PaymentDetailDialog(
    payment: Payment?,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(
                    text = "Payment Details",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = robotoFontFamily
                )
                Spacer(modifier = Modifier.height(16.dp))

                payment?.let {
                    Text("Amount: $${it.amount}")
                    Text("Payment Method: ${it.paymentMethod}")
                    Text("Date: ${formatDate(it.timestamp)}")
                    if (it.paymentMethod != "Mpesa") {
                        Text("Card Number: ${it.cardNumber}")
                        Text("Card Holder: ${it.cardHolderName}")
                        Text("Expiry Date: ${it.expiryDate}")
                    } else {
                        Text("Phone Number: ${it.phoneNumber}")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Close")
                }

            }
        }
    }
}

@Composable
fun AddEditPaymentDialog(
    payment: Payment?,
    onDismiss: () -> Unit,
    onSave: (Payment) -> Unit
) {
    var amount by remember { mutableStateOf(payment?.amount?.toString() ?: "") }
    var paymentMethod by remember { mutableStateOf(payment?.paymentMethod ?: "Credit Card") }
    var cardNumber by remember { mutableStateOf(payment?.cardNumber ?: "") }
    var cardHolderName by remember { mutableStateOf(payment?.cardHolderName ?: "") }
    var expiryDate by remember { mutableStateOf(payment?.expiryDate ?: "") }
    var cvv by remember { mutableStateOf(payment?.cvv ?: "") }
    var phoneNumber by remember { mutableStateOf(payment?.phoneNumber?.toString() ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(
                    text = if (payment == null) "Add Payment" else "Edit Payment",
                    fontFamily = robotoFontFamily,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                PaymentMethodsSelection(
                    paymentMethod = paymentMethod,
                    onPaymentMethodChange = { paymentMethod = it },
                    cardNumber = cardNumber,
                    onCardNumberChange = { cardNumber = it },
                    cardHolderName = cardHolderName,
                    onCardHolderNameChange = { cardHolderName = it },
                    expiryDate = expiryDate,
                    onExpiryDateChange = { expiryDate = it },
                    cvv = cvv,
                    onCvvChange = { cvv = it },
                    phoneNumber = phoneNumber,
                    onPhoneNumberChange = { phoneNumber = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val newPayment = Payment(
                                id = payment?.id ?: 0,
                                amount = amount.toDoubleOrNull() ?: 0.0,
                                paymentMethod = paymentMethod,
                                cardNumber = if (paymentMethod != "Mpesa") cardNumber else null,
                                cardHolderName = if (paymentMethod != "Mpesa") cardHolderName else null,
                                expiryDate = if (paymentMethod != "Mpesa") expiryDate else null,
                                cvv = if (paymentMethod != "Mpesa") cvv else null,
                                phoneNumber = if (paymentMethod == "Mpesa") phoneNumber.toIntOrNull() else null,
                                timestamp = payment?.timestamp ?: System.currentTimeMillis()
                            )
                            onSave(newPayment)
                        }
                    ) {
                        Text("Save")
                    }
                }

            }
        }
    }
}

@Composable
fun PaymentCard(
    payment: Payment,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onViewClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(
                text = "Amount: $${payment.amount}",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = robotoFontFamily
            )
            Text(
                text = "Date: ${formatDate(payment.timestamp)}",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = robotoFontFamily
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onViewClick) {
                    Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "View")
                }
                IconButton(onClick = onEditClick) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

fun formatDate(timestamp: Long): String {
 val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarPayment(navController :NavController) {
    TopAppBar(
        // TODO:  Text(text = "Workout Screen" , textAlign = TextAlign.Center)
        title = {

            Column(
                modifier = Modifier
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
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {

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

