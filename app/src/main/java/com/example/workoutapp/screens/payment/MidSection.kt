package com.example.workoutapp.screens.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutapp.data.Payment
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.PaymentViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.toDuration

@Composable
fun MidSectionPay(modifier: Modifier = Modifier) {

    var showPaymentSelection by remember { mutableStateOf(false) }

    val viewModel : PaymentViewModel = hiltViewModel()
    PaymentContent(
        paymentViewModel = viewModel,
        onHidePaymentSelection = { showPaymentSelection = false },
        showPaymentSelection = showPaymentSelection,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun MidSectionPreview() {
    WorkoutAppTheme {
        MidSectionPay()
    }
}



@Composable
fun PaymentItem(payment: Payment) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateString = dateFormat.format(Date(payment.timestamp))

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {

            Column {
                Text(
                    text = "Date: $dateString",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Method: ${payment.paymentMethod}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "Amount: ${payment.amount}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentItemPreview() {
    WorkoutAppTheme {
        PaymentItem(payment = Payment(id = 12, amount = 99.99, paymentMethod = "Debit card", cardNumber = "0123456", cardHolderName = "Stacy Apeyon", timestamp = 1721322178449))
    }
}