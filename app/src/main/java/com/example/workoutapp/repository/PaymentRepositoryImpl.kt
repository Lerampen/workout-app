package com.example.workoutapp.repository

import com.example.workoutapp.data.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepositoryImpl {
    fun getAllPayments(): Flow<List<Payment>>
    fun getPaymentById(paymentId : Int): Flow<Payment>

    suspend fun insertPayment(payment: Payment)
    suspend fun deletePayment(payment: Payment)
    suspend fun updatePayment(payment: Payment)
}
