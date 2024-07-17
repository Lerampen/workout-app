package com.example.workoutapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(val label: String, val icon: ImageVector, val screens: Screens)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Outlined.Home, Screens.Home),
    BottomNavItem("Workouts", Icons.Outlined.FitnessCenter, screens = Screens.Workouts),
    BottomNavItem("Nutrition", Icons.Outlined.Restaurant, screens = Screens.Nutrition),
    BottomNavItem("Payment", Icons.Outlined.Payment, screens = Screens.Payment),
    BottomNavItem("Profile", Icons.Outlined.PersonOutline, screens = Screens.Profile)
)