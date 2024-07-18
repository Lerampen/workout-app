package com.example.workoutapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var amount : Double,
    var paymentMethod : String,
    var cardNumber : String? =  null,
    var cardHolderName : String?  = null,
    var expiryDate : String? = null,
    var cvv : String? = null,
    var phoneNumber: Int? = null,
    val  timestamp: Long // This will store the payment date as a timestamp

)
