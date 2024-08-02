package com.example.workoutapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.Payment
import com.example.workoutapp.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentManagementViewModel @Inject constructor(
    application: Application,
    private val repository: PaymentRepository
) : AndroidViewModel(application = application) {

    val allPayments = repository.getAllPayments()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val filteredPayments = combine(allPayments, _searchQuery) { payments, query ->
        if (query.isBlank()) {
            payments
        } else {
            payments.filter { payment ->
                // Implement your search logic here
                payment.toString().contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

//    private val _allPayments = MutableStateFlow<List<Payment>>(emptyList())
//    val allPayments: StateFlow<List<Payment>> = _allPayments



    fun insert(payment: Payment) = viewModelScope.launch {
        repository.insertPayment(payment)
    }

    fun update(payment: Payment) = viewModelScope.launch {
        repository.updatePayment(payment)
    }

    fun delete(payment: Payment) = viewModelScope.launch {
        repository.deletePayment(payment)
    }

//    fun getPaymentById(id: Int): LiveData<Payment?> = liveData {
//        emit(paymentRepository.getPaymentById(id).firstOrNull())
//    }

    fun searchPayments(query: String) {

                _searchQuery.value = query


    }

    

}