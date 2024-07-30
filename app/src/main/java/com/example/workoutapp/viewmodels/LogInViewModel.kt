package com.example.workoutapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workoutapp.data.Resource
import com.example.workoutapp.data.User
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


    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    fun loginUser(email : String, password: String){
        viewModelScope.launch {

            authRepository.loginUser(email, password).collect{result ->
                _loginState.value = result

                if (result is Resource.Success) {
                    checkAdminStatus(email)
                }

            }
        }
    }

    private fun checkAdminStatus(email: String) {

        firestore.collection("users")
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {

                    val document = querySnapshot.documents[0]
                    val user = document.toObject(User::class.java)
                    if (user != null) {
                        Log.d("LogInViewModel", "User found: ${user.email}, isAdmin: ${user.admin}")
                        _isAdmin.value = user.admin

                    }

                } else {
                    Log.d("LogInViewModel", "No user found with email: $email")

                    _isAdmin.value = false
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LogInViewModel", "Error checking admin status", exception)
                _isAdmin.value = false
            }
    }
    }


