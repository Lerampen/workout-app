package com.example.workoutapp.screens.payment

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutapp.data.Payment
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.PaymentViewModel

@Composable
fun PaymentContent(modifier: Modifier, paymentViewModel: PaymentViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        paymentViewModel.getAllPayments()
    }
    val context = LocalContext.current
    var paymentMethod by remember {
        mutableStateOf("Credit Card")
    }

    var cardNumber by remember {
        mutableStateOf("")
    }
    var cardHolderName by remember {
        mutableStateOf("")
    }
    var expiryDate by remember {
        mutableStateOf("")
    }
    var cvv by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    val payments = listOf(
        Payments("2024-07-01", "$50.00", "Credit Card"),
        Payments("2024-07-11", "$75.00", "Credit Card"),
        Payments("2024-07-21", "$100.00", "Debit Card"),
        Payments("2024-06-21", "$110.00", "Debit Card")


    )
    val allPayments by paymentViewModel.allPayments.collectAsState()
    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {


        Column(
            modifier = Modifier
                .fillMaxSize()
             ,

        ) {
            PaymentList(payments = allPayments, Modifier.padding(bottom = 16.dp))
            Spacer(modifier = Modifier.height(12.dp))
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
            PaymentAmount(amount = "$99.99")
            Spacer(modifier = Modifier.height(16.dp))
            PaymentButton(
                context = context,
                paymentMethod = paymentMethod,
                amount = 99.99,
                cardNumber = if (paymentMethod != "Mpesa") cardNumber else null,
                cardHolderName = if (paymentMethod != "Mpesa") cardHolderName else null,
                expiryDate = if (paymentMethod != "Mpesa") expiryDate else null,
                cvv = if (paymentMethod != "Mpesa") cvv else null,
                phoneNumber = if (paymentMethod == "Mpesa") phoneNumber.toIntOrNull() else null
            ) { payment ->
                paymentViewModel.insert(payment)
            }
            Spacer(modifier = modifier.height(16.dp))
            SecurePaymentText()
        }
    }
}

@Composable
fun PaymentList(payments: List<Payment>, modifier: Modifier) {

    Column(modifier = modifier){
        Text(
            text = "This Month's Payments",
            fontFamily = robotoFontFamily,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))  // Added space between title and lis
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(payments) { payment ->
                PaymentItem(payment = payment)
            }
        }
    }

}

@Composable
fun PaymentMethodsSelection(
    paymentMethod: String,
    onPaymentMethodChange: (String) -> Unit,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    cardHolderName: String,
    onCardHolderNameChange: (String) -> Unit,
    expiryDate: String,
    onExpiryDateChange: (String) -> Unit,
    cvv: String,
    onCvvChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    Text(
        text = "Choose your payment method",
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        style = MaterialTheme.typography.headlineSmall
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { onPaymentMethodChange("Credit Card") },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Credit Card", fontSize = 12.sp, fontFamily = robotoFontFamily)
        }

        Button(
            onClick = { onPaymentMethodChange("Debit Card") },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Debit Card", fontSize = 12.sp, fontFamily = robotoFontFamily)
        }

        Button(
            onClick = { onPaymentMethodChange("Mpesa") },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Mpesa", fontSize = 12.sp, fontFamily = robotoFontFamily)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    when(paymentMethod){
        "Credit Card","Debit Card" -> {
            CreditOrDebitCardForm(
                cardNumber = cardNumber,
                onCardNumberChange = onCardNumberChange,
                cardHolderName = cardHolderName ,
                onCardHolderNameChange = onCardHolderNameChange,
                expiryDate = expiryDate,
                onExpiryDateChange = onExpiryDateChange,
                cvv = cvv,
                onCvvChange = onCvvChange
            )
        }
        "Mpesa" ->{
            MpesaForm(phoneNumber = phoneNumber, onPhoneNumberChange = onPhoneNumberChange)
        }
    }
}

@Composable
fun PaymentAmount(amount : String) {
    Text(
        text = "Amount to be paid: $amount",
        fontFamily = robotoFontFamily,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PaymentButton(
    context: Context,
    paymentMethod: String,
    amount: Double,
    cardNumber: String?,
    cardHolderName: String?,
    expiryDate: String?,
    cvv: String?,
    phoneNumber: Int?,
    onPayNowClick: (Payment) -> Unit
) {

    Button(onClick = {
        val paymentMethodString = when(paymentMethod){
            "Credit Card" -> "Credit Card"
            "Debit Card" -> "Debit Card"
            else -> "Mpesa"
        }

//        Create  a new payment object
        val payment = Payment(
            amount = amount,
            paymentMethod = paymentMethodString,
            cardNumber = cardNumber,
            cardHolderName = cardHolderName,
            expiryDate = expiryDate,
            cvv = cvv,
            phoneNumber = phoneNumber,
            timestamp = System.currentTimeMillis()
        )
        // Insert the payment into the database
        onPayNowClick(payment)
        Toast.makeText(
            context,
            "$paymentMethodString Payment Successful",
            Toast.LENGTH_LONG

        ).show()
    },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {

        Text(
            text = "Pay Now",
            fontFamily = robotoFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SecurePaymentText(modifier: Modifier = Modifier) {
    Text(
        text = "Secure Payment",
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )
}
@Composable
fun MpesaForm(phoneNumber: String, onPhoneNumberChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = { Text(text = "Phone Number")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MpesaFormPreview() {
    WorkoutAppTheme {
        MpesaForm(phoneNumber = "0712345678", onPhoneNumberChange = {})

    }
}

@Composable
fun CreditOrDebitCardForm(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    cardHolderName: String,
    onCardHolderNameChange: (String) -> Unit,
    expiryDate: String,
    onExpiryDateChange: (String) -> Unit,
    cvv: String,
    onCvvChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TextField(
            value = cardNumber,
            onValueChange = onCardNumberChange,
            label = { Text(
                text = "Card Number",
                fontSize = 16.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium
            ) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = cardHolderName,
            onValueChange = onCardHolderNameChange,
            label = { Text(
            text = "Card Holder Name",
            fontSize = 16.sp,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium
        ) },
            keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
        )

        Row (horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()){
            TextField(
                value = expiryDate,
                onValueChange = onExpiryDateChange,
                label = { Text(
                    text = "Expiry Date",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium
                ) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = cvv,
                onValueChange = onCvvChange,
                label = { Text(
                    text = "CVV",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium
                ) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentContentPreview() {
    WorkoutAppTheme {
        PaymentContent(modifier = Modifier)
    }
}

