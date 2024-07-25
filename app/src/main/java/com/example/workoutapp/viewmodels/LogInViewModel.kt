package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workoutapp.data.Resource
import com.example.workoutapp.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    application: Application,
    private  val authRepository: AuthRepository,
    private val firestore: FirebaseFirestore
    ) : AndroidViewModel(application){
        private val _loginState = MutableStateFlow<Resource<AuthResult>>(Resource.Loading())
        val loginState: StateFlow<Resource<AuthResult>> = _loginState

    fun loginUser(email : String, password: String){
        viewModelScope.launch {
            authRepository.loginUser(email, password).collect{result ->
                _loginState.value = result
            }
        }
    }

    suspend fun checkIfAdmin(email: String?): Boolean {
        return  try {
            val doc = email?.let { firestore.collection("users").document(it).get().await() }
            doc?.getBoolean("isAdmin") ?: false

        }catch (e : Exception){
            false
        }
    }
}