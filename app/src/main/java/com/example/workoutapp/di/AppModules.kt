package com.example.workoutapp.di

import android.content.Context
import androidx.room.Room
import com.example.workoutapp.data.AppDatabase
import com.example.workoutapp.data.MealDao
import com.example.workoutapp.data.PaymentDao
import com.example.workoutapp.repository.PaymentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    @Singleton
    fun provideMealDao(db : AppDatabase) : MealDao{
        return db.mealDao()
    }

    @Provides
    @Singleton
    fun providePaymentDao(db : AppDatabase) : PaymentDao{
        return db.paymentDao()
    }


    @Provides
    @Singleton
    fun providePaymentRepository(paymentDao: PaymentDao) : PaymentRepository{
        return PaymentRepository(paymentDao)
    }





}