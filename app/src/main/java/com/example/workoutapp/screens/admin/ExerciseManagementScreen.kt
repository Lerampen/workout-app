package com.example.workoutapp.screens.admin

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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.workoutapp.data.Exercise
import com.example.workoutapp.viewmodels.WorkoutManagementViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseManagementScreen(
    workoutId: Int,
    viewModel: WorkoutManagementViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val exercises by viewModel.exercises.collectAsState()
    var showAddEditDialog by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    LaunchedEffect(workoutId) {
        viewModel.fetchExercisesForWorkout(workoutId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Exercises") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedExercise = null
                showAddEditDialog = true
            }) {
                Icon(Icons.Outlined.Add, contentDescription = "Add Exercise")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(exercises) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onEditClick = {
                        selectedExercise = exercise
                        showAddEditDialog = true
                    },
                    onDeleteClick = {
                        viewModel.deleteExercise(exercise)
                    }
                )
            }
        }

        if (showAddEditDialog) {
            AddEditExerciseDialog(
                workoutId = workoutId,
                exercise = selectedExercise,
                onDismiss = { showAddEditDialog = false },
                onSave = { updatedExercise ->
                    if (selectedExercise == null) {
                        viewModel.addExercise(updatedExercise.copy(workoutId = workoutId))
                    } else {
                        viewModel.updateExercise(updatedExercise)
                    }
                    showAddEditDialog = false
                }
            )
        }
    }
}

@Composable
fun AddEditExerciseDialog(
    workoutId: Int, // Add this parameter to receive the workoutId
    exercise: Exercise?,
    onDismiss: () -> Unit,
    onSave: (Exercise) -> Unit
) {
    var exerciseName by remember { mutableStateOf(exercise?.exerciseName ?: "") }
    var exerciseIllustration by remember { mutableStateOf(exercise?.exerciseIllustration ?: "") }
    var repetitions by remember { mutableStateOf(exercise?.repetitions?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (exercise == null) "Add Exercise" else "Edit Exercise") },
        text = {
            Column {
                OutlinedTextField(
                    value = exerciseName,
                    onValueChange = { exerciseName = it },
                    label = { Text("Exercise Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = exerciseIllustration,
                    onValueChange = { exerciseIllustration = it },
                    label = { Text("Illustration URL") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = repetitions,
                    onValueChange = { repetitions = it },
                    label = { Text("Repetitions") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    Exercise(
                        id = exercise?.id ?: UUID.randomUUID().toString(),
                        workoutId = exercise?.workoutId ?: 0, // This will be set correctly when adding
                        exerciseName = exerciseName,
                        exerciseIllustration = exerciseIllustration,
                        repetitions = repetitions.toIntOrNull() ?: 0
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
@Composable
fun ExerciseItem(
    exercise: Exercise,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = exercise.exerciseIllustration,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = exercise.exerciseName, style = MaterialTheme.typography.titleMedium)
                Text(text = "${exercise.repetitions} reps", style = MaterialTheme.typography.bodyMedium)
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