package com.example.workoutapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Meal::class, Payment::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun paymentDao(): PaymentDao

//    companion object{
//        @Volatile
//        private var INSTANCE : AppDatabase? = null
//
//        fun getDatabase(context: Context) : AppDatabase{
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context = context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE   =  instance
//                instance
//            }
//        }
//
//
//    }
}