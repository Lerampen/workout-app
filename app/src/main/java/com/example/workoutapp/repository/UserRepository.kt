package com.example.workoutapp.repository

import com.example.workoutapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    private suspend fun saveUserToFirestore(user: User){
        firestore.collection("users").document(user.email).set(user).await()
    }
}
