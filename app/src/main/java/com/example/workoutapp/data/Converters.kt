package com.example.workoutapp.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromMealType(value : MealTypes) : String{
        return value.name
    }

    @TypeConverter
    fun toMealType(value : String) : MealTypes{
        return enumValueOf(value)
    }
}