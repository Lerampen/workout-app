package com.example.workoutapp.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.screens.home.MealsCard
import com.example.workoutapp.screens.nutrition.AddMealButton
import com.example.workoutapp.screens.nutrition.CalorieCard
import com.example.workoutapp.screens.nutrition.MealsSectionNutrition
import com.example.workoutapp.screens.nutrition.TopSectionNutr
import com.example.workoutapp.ui.theme.WorkoutAppTheme

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Scaffold (
        topBar =  { TopSectionProf() },

        ){ paddingValues ->

        Column (modifier = modifier.padding(paddingValues)){
            ProfileImage()
            MidSection()
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    WorkoutAppTheme {
        Profile()
    }
}