package com.example.workoutapp.di

import android.content.Context
import androidx.room.Room
import com.example.workoutapp.data.AppDatabase
import com.example.workoutapp.data.ExerciseDao
import com.example.workoutapp.data.MealDao
import com.example.workoutapp.data.NutritionDao
import com.example.workoutapp.data.PaymentDao
import com.example.workoutapp.data.WorkoutDao
import com.example.workoutapp.repository.AuthRepository
import com.example.workoutapp.repository.AuthRepositoryImpl
import com.example.workoutapp.repository.ExerciseRepository
import com.example.workoutapp.repository.PaymentRepository
import com.example.workoutapp.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideWorkoutDao(db : AppDatabase) : WorkoutDao {
        return db.workoutDao()
    }
    @Provides
    @Singleton
    fun provideExerciseDao(db : AppDatabase) : ExerciseDao {
        return db.exerciseDao()
    }
    @Provides
    @Singleton
    fun provideNutritionDao(db : AppDatabase) : NutritionDao {
        return db.nutritionDao()
    }


    @Provides
    @Singleton
    fun providePaymentRepository(paymentDao: PaymentDao) : PaymentRepository{
        return PaymentRepository(paymentDao)
    }
    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao) : ExerciseRepository{
        return ExerciseRepository(exerciseDao)
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideRepositortyImpl(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore) : UserRepository {
        return UserRepository(firebaseAuth, firestore)
    }
    @Singleton
    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository {
        return authRepositoryImpl
    }
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }





}