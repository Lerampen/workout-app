package com.example.workoutapp.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.screens.home.MealsCard
import com.example.workoutapp.screens.nutrition.AddMealButton
import com.example.workoutapp.screens.nutrition.CalorieCard
import com.example.workoutapp.screens.nutrition.MealsSectionNutrition
import com.example.workoutapp.screens.nutrition.TopSectionNutr
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.viewmodels.ProfileViewModel
import com.example.workoutapp.viewmodels.UsersMgmtViewModel

@Composable
fun Profile(
    navController: NavController,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel,
    viewModel: UsersMgmtViewModel = hiltViewModel()

) {
    BackHandler {
        navController.popBackStack()
    }
    val  currentUser by viewModel.currentUser.collectAsState() // Assuming the first user is the current user

    LaunchedEffect(Unit) {
        viewModel.fetchCurrentUser()
    }
    Scaffold (
        topBar =  { TopSectionProf(navController = navController) },

        ){ paddingValues ->

        Column (
            modifier = modifier
            .padding(paddingValues)
            ){
            ProfileImage(currentUser = currentUser)
            MidSection(navController = navController, profileViewModel = profileViewModel )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        val viewModel = hiltViewModel<ProfileViewModel>()
        Profile(navController = navController, profileViewModel = viewModel)
    }
}