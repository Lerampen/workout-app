package com.example.workoutapp.repository

import com.example.workoutapp.data.Payment
import com.example.workoutapp.data.PaymentDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PaymentRepository @Inject constructor(private val paymentDao: PaymentDao): PaymentRepositoryImpl {
    override fun getAllPayments(): Flow<List<Payment>> {
       return paymentDao.getAllPayments()
    }

    override fun getPaymentById(paymentId: Int): Flow<Payment> {
      return  paymentDao.getPaymentById(paymentId)
    }

    override suspend fun insertPayment(payment: Payment) {
        return paymentDao.insert(payment)
    }

    override suspend fun deletePayment(payment: Payment) {
        return paymentDao.delete(payment)
    }

    override suspend fun updatePayment(payment: Payment) {
        return paymentDao.update(payment)
    }

    override fun searchPayments(query: String): Flow<List<Payment>> {
         return paymentDao.searchPayments(query)
    }


}