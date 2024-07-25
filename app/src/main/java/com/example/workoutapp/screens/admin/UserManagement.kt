package com.example.workoutapp.screens.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.google.firebase.firestore.auth.User

@Composable
fun UseManagementScreen(modifier: Modifier = Modifier) {
    Scaffold (topBar = { TopbarUser() }){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
           Text(
               text = "Users",
               style = MaterialTheme.typography.titleLarge,
               fontFamily = robotoFontFamily,
               modifier = Modifier.padding(bottom = 16.dp)
           )
            val users = listOf(
                Users("John Doe", "john.doe@example.com"),
                Users("Jane Smith", "jane.smith@example.com"),
                Users("Mike Johnson", "mike.johnson@example.com")
            )
            LazyColumn {
                items(users){user->
                    UserCard(
                        user = user,
                        onEditClick = {
                            // handle edit user
                        },
                        onDeleteClick = {
//                        Handle delete user
                        }
                    )
                }
            }
        }

    }
}

// TODO: include a top app bar 


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarUser(modifier: Modifier = Modifier) {
    TopAppBar(
        // TODO:  Text(text = "Workout Screen" , textAlign = TextAlign.Center)
        title = {

            Column(
                modifier = modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ){

                Text(
                    text = " User Management ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { /*navController.popBackStack()*/ }) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Back arrow"
                )
            } },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}

// TODO: a card for the users

@Composable
fun UserCard(user: Users, onEditClick : () -> Unit, onDeleteClick : () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onEditClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = robotoFontFamily,
                        fontSize = 20.sp
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = robotoFontFamily,
                        fontSize = 16.sp
                    )

                }
                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit",
                            tint = Color.Cyan
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun UserManagementScreenPreview() {
    WorkoutAppTheme {
        UseManagementScreen()
    }
}
data class  Users(val name : String, val email : String)
