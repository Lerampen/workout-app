package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.Payment
import com.example.workoutapp.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: PaymentRepository) : ViewModel() {
   private val _allPayments = MutableStateFlow<List<Payment>>(emptyList())
           var allPayments :StateFlow<List<Payment>> =  _allPayments

    private val _selectedPayment = MutableStateFlow<Payment?>(null)
    val selectedPayment : StateFlow<Payment?> = _selectedPayment

    init{
        getAllPayments()
    }

    fun getPaymentById(paymentId : Int) {
        viewModelScope.launch {
            repository.getPaymentById(paymentId).collect { payment ->
                _selectedPayment.value  = payment
            }
        }
    }
    fun getAllPayments() {
        viewModelScope.launch {
            repository.getAllPayments().collect {
                _allPayments.value  = it
            }
        }
    }
    fun insert(payment: Payment) = viewModelScope.launch {
        repository.insertPayment(payment)
        repository.getAllPayments().collect{
            _allPayments.value = it
        }
    }

    fun delete(payment: Payment) = viewModelScope.launch {
        repository.deletePayment(payment)
        repository.getAllPayments().collect{
            _allPayments.value = it
        }
    }

    fun update(payment: Payment) = viewModelScope.launch {
        repository.updatePayment(payment)
        repository.getAllPayments().collect{
            _allPayments.value = it
        }
    }
}