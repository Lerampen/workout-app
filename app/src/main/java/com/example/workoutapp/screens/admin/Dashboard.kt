package com.example.workoutapp.screens.admin

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.DashboardViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashBoard(modifier: Modifier = Modifier, navController: NavController, dashboardViewModel: DashboardViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Admin Dashboard") })
        },
        content = { paddingValues ->
            /** TODO: navigate to user management screen*/
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize().padding(paddingValues)

            ) {
               item {
                   DashboardCards(
                       title = "User Management",
                       onClick = {
                           Toast.makeText(context, "Navigating to  user management screen!", Toast.LENGTH_SHORT).show()
                           navController.navigate("user_management") // Update with your login screen route
                       },
                       text = "Add User",
                       imageVector = Icons.Outlined.Person

                   )
               }
               item {
                   DashboardCards(
                       title = "Workout Management",
                       onClick = {
                           Toast.makeText(context, "Navigating to  workout management screen!", Toast.LENGTH_SHORT).show()
                           navController.navigate("workout_management")
                       },
                       text = " Add Workouts",
                       imageVector = Icons.Outlined.FitnessCenter

                   )
               }
                item {
                    DashboardCards(
                        title = "Nutrition Management",
                        onClick = { navController.navigate("nutrition_management") },
                        text = "Manage Nutrition Plans",
                        imageVector = Icons.Outlined.Restaurant
                    )
                }
               item{
                   DashboardCards(
                       title = "Payment Management",
                       onClick = { navController.navigate("payment_management") },
                       text = "View Payments",
                       imageVector = Icons.Outlined.Payments

                   )
               }
                item {
                    DashboardCards(
                        title = "System Settings",
                        onClick = {/** TODO: navigate to user management screen*/ },
                        text = "Settings",
                        imageVector = Icons.Outlined.Settings

                    )
                }

                item {
                    DashboardCards(
                        title = "Logout",
                        onClick = {
                            dashboardViewModel.signOut()
                            Toast.makeText(context, "Logged Out Successfully!", Toast.LENGTH_SHORT).show()
                            navController.navigate("login_screen") // Update with your login screen route

                        },
                        text = "Logout",
                        imageVector = Icons.AutoMirrored.Outlined.Logout

                    )
                }


            }
        }

    )
}

@Composable
fun DashboardCards(
    title: String,
    text: String,
   onClick: () -> Unit,
    imageVector: ImageVector
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable ( onClick = onClick ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(16.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = robotoFontFamily
                )
                Spacer(modifier = Modifier.height(8.dp))
              Row  {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = robotoFontFamily
                    )
                  Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = imageVector, contentDescription = null, tint = Color.Green)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AdminDashboardPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        val viewModel = hiltViewModel<DashboardViewModel>()
        AdminDashBoard(navController = navController, dashboardViewModel = viewModel)
    }
}
