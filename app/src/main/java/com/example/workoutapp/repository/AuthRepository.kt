package com.example.workoutapp.repository

import com.example.workoutapp.data.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    /*
      our Repository interface, which defines the functions we will use
       */
    /*
    We need two function for authentication, i.e,
    1. Registering a new user
    2. Logging in a new user
    3. Signing/Logging out a user
     */
    fun registerUser(fname : String, lname : String, email : String, password : String) : Flow<Resource<AuthResult>>
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun signOut()
}
