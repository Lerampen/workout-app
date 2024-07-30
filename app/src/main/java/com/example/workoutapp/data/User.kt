package com.example.workoutapp.data

import com.google.firebase.firestore.PropertyName

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val admin: Boolean = false,
    )
