package com.example.workoutapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert
    suspend fun insert(payment: Payment)

    @Update
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    @Query("Select * from  payments")
    fun getAllPayments(): Flow<List<Payment>>

    @Query("Select * from  payments where id = :id")
    fun getPaymentById(id : Int): Flow<Payment>
}