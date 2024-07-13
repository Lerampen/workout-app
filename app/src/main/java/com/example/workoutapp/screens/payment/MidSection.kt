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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.screens.profile.MidSection
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun MidSectionPay(modifier: Modifier = Modifier) {
    PaymentContent(modifier = modifier)
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
                    text = "Date: ${payment.date}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Method: ${payment.method}",
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
        PaymentItem(payment = Payment(date = "2024-07-01", amount = "$50.00", method = "Credit Card"))
    }
}