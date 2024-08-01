package com.example.workoutapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.User
import com.example.workoutapp.repository.AuthRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UsersMgmtViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository,
    private  val firestore: FirebaseFirestore
) : AndroidViewModel(application = application ){

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users : StateFlow<List<User>> = _users

    fun fetchUsersFromFireStore(){
        viewModelScope.launch {
            try{
                val usersList = mutableListOf<User>()
                val result = firestore.collection("users").get().await()
                for (document in result.documents) {
                    val user = document.toObject(User::class.java)
                    if (user != null) {
                        usersList.add(user)
                    }
                }
                _users.value = usersList
                Log.d("UsersMgmtViewModel", "Fetched users: $usersList")
            }catch (e: Exception){
                Log.e("UserMgmtViewModel", "Error fetching users", e)
            }
        }
    }

    fun deleteUser(user: User) {

        viewModelScope.launch {
            firestore.collection("users").document(user.email).delete().await()
            fetchUsersFromFireStore() // Refresh the list after deletion
            Log.e("UserMgmtViewModel", "Deleted user")

        }
    }
    fun updateUser(user: User) {
        viewModelScope.launch {
            firestore.collection("users").document(user.email).set(user).await()
            fetchUsersFromFireStore() // Refresh the list after update
        }
    }


}