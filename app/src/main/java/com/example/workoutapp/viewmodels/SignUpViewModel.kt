package com.example.workoutapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.Resource
import com.example.workoutapp.data.User
import com.example.workoutapp.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository,
    private val firestore: FirebaseFirestore
): AndroidViewModel(application) {
        private  val _signInState = MutableStateFlow<Resource<AuthResult>>(value = Resource.Loading())
    val signInState : StateFlow<Resource<AuthResult>> = _signInState

    fun signUpUser(fname: String, lname : String, email : String, password : String, isAdmin : Boolean){
        viewModelScope.launch {
            authRepository.registerUser(fname, lname, email, password).collect{result ->
                _signInState.value = result
                if (result is Resource.Success){
                    val user =  User(firstName = fname, lastName = lname, email = email, isAdmin = isAdmin)
                    saveUserToFireStore(user)
                }
            }
        }
    }

    private suspend fun saveUserToFireStore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }
}