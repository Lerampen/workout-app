package com.example.workoutapp.screens.admin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.workoutapp.data.Workout
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.WorkoutManagementViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutManagementScreen(
    navController: NavController,
    viewModel: WorkoutManagementViewModel = hiltViewModel()
) {

    BackHandler {
        navController.popBackStack()
    }

    val workouts by viewModel.workouts.collectAsState()
    var showAddEditDialog by remember {
        mutableStateOf(false)
    }
    var selectedWorkout by remember {
        mutableStateOf<Workout?>(null)
    }
    var searchQuery by remember { mutableStateOf("") }


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(
                    "Workout Management",
                    fontFamily = robotoFontFamily,
                    style = MaterialTheme.typography.titleLarge
                ) },
                navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = "Back")
                }
            },
                actions = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search") }
                )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedWorkout = null
                showAddEditDialog = true
                },
                containerColor = Color.Black,
                contentColor = Color.White
                ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Workout" )
            }
        }
    ){ paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchWorkouts(it)

                },
                label = {
                    Text(text = "Search Workouts", fontFamily = robotoFontFamily)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(workouts) { workout ->
                    AdminWorkoutCard(
                        workout = workout,
                        onEditClick = {
                            selectedWorkout = workout
                            showAddEditDialog = true
                        },
                        onDeleteClick = {
                            viewModel.deleteWorkout(workout)
                        }
                    )
                }


            }
        }
        if (showAddEditDialog){
            AddEditWorkoutDialog(
                workout = selectedWorkout,
                onDismiss = { showAddEditDialog = false },
                onSave = { updateWorkout ->
                    if (selectedWorkout == null){
                        viewModel.addWorkout(updateWorkout)
                    }else {
                        viewModel.updateWorkout(updateWorkout)
                    }
                    showAddEditDialog = false
                }
            )
        }
    }
}

@Composable
fun AddEditWorkoutDialog(workout: Workout?, onDismiss: () -> Unit, onSave: (Workout) -> Unit) {

    var day by remember { mutableStateOf(workout?.day ?: "") }
    var duration by remember { mutableStateOf(workout?.duration?.toString() ?: "") }
     var exerciseCount by remember { mutableStateOf(workout?.exerciseCount?.toString() ?: "") }
    var imageUrl by remember { mutableStateOf(workout?.imageUrl ?: "") }

    AlertDialog(onDismissRequest = onDismiss,
        title = {Text(
            if (workout == null) "Add Workout" else "Edit Workout",
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )},
        text = {
            Column {
                OutlinedTextField(
                    value = day,
                    onValueChange = { day = it },
                    label = {
                        Text(
                            text = "Day",
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                        )
                    },
                    modifier = Modifier.fillMaxWidth()

                )
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = {
                        Text(
                            text = "Duration (minutes)",
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()

                )
                OutlinedTextField(
                    value = exerciseCount,
                    onValueChange = { exerciseCount = it },
                    label = {
                        Text(
                            text = "Number of Exercises",
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()

                )
                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = {
                        Text(
                            text = "Image URL",
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier.fillMaxWidth()

                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    Workout(
                        id = workout?.id ?: 0,
                        day = day,
                        duration = duration.toIntOrNull() ?: 0,
                        exerciseCount = exerciseCount.toIntOrNull() ?: 0,
                        imageUrl = imageUrl
                    )
                )
            }) {
            Text(
                text = "Save",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancel",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            }
        }
        )
}

@Composable
fun AdminWorkoutCard(workout: Workout, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = workout.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column (modifier = Modifier.weight(1f)){
                Text(
                    text = "Day ${workout.day}",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = robotoFontFamily
                )
                Text(
                    text = "${workout.duration} Mins • ${workout.exerciseCount} Exercises",
                    fontFamily = robotoFontFamily,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
            IconButton(onClick = onEditClick) {
                Icon(Icons.Outlined.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Outlined.Delete, contentDescription = "Delete")
            }
        }
    }
}
