package com.example.workoutapp.screens.payment

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun PaymentContent(modifier: Modifier) {

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
        Payment("2024-07-01", "$50.00", "Credit Card"),
        Payment("2024-07-11", "$75.00", "Credit Card"),
        Payment("2024-07-21", "$100.00", "Debit Card")

    )
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "This Month's Payments", fontFamily = robotoFontFamily, style = MaterialTheme.typography.headlineSmall)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(payments){ payment ->
                PaymentItem(payment = payment)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(text = "Choose your payment method", style = MaterialTheme.typography.headlineSmall)
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            Button(
                onClick = { paymentMethod = "Credit Card" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (paymentMethod == "Credit Card ") Color.Black else Color.LightGray,
                    contentColor = if (paymentMethod == "Credit Card") Color.White else Color.Black


                )
            ) {
                Text(text = "Credit Card", fontSize = 12.sp, fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)
            }
            Button(
                onClick = { paymentMethod = "Debit Card" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (paymentMethod == "Debit Card ") Color.Black else Color.LightGray,
                    contentColor = if (paymentMethod == "Debit Card") Color.Black else Color.White


                )
            ) {
                Text(text = "Credit Card", fontSize = 12.sp, fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)
            }
            Button(
                onClick = { paymentMethod = "Mpesa" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (paymentMethod == "Mpesa") Color.Black else Color.LightGray,
                    contentColor = if (paymentMethod == "Mpesa") Color.Black else Color.White


                )
            ) {
                Text(text = "Mpesa", fontSize = 12.sp, fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)
            }
        }
        when(paymentMethod){
            "Credit Card","Debit Card" ->{
                CreditOrDebitCardForm(
                    cardNumber = cardNumber,
                    onCardNumberChange = { cardNumber = it },
                    cardHolderName = cardHolderName,
                    onCardHolderNameChange = { cardHolderName = it },
                    expiryDate = expiryDate,
                    onExpiryDateChange = { expiryDate = it },
                    cvv = cvv,
                    onCvvChange = { cvv = it }
                )
            }
            "Mpesa" ->{
                MpesaForm(
                    phoneNumber = phoneNumber,
                    onPhoneNumberChange = { phoneNumber = it}
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Amount to be paid: \$99.99",
            fontFamily = robotoFontFamily,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val paymentMethodString = when (paymentMethod) {
                    "Credit Card" -> "Credit Card"
                    "Debit Card" -> "Debit Card"
                    else -> "Mpesa"
                }
                Toast.makeText(
                    context,
                    "$paymentMethodString Payment Successful!",
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(
                text = "Pay Now",
                fontFamily = robotoFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))


        }
        Text(
            text = "Secure Payment",
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)

            )
    }
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
                value = cardNumber,
        onValueChange = onCardNumberChange,
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