package com.example.workoutapp.screens.workouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.workoutapp.data.Exercise
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.ExerciseViewModel

@Composable
fun ExerciseList(
   day :String,
    navController: NavController,
    viewModel: ExerciseViewModel = hiltViewModel(),
) {
    val exercises by viewModel.exercises.collectAsState()

    LaunchedEffect(day) {
        viewModel.fetchExercisesForDay(day = day)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(exercises){ exercise ->
            ExerciseItem(
                exercise = exercise,
                onClick = {
                    navController.navigate("exercise_detail/${exercise.id}")
                },
                modifier = Modifier
            )
        }

    }
}

@Composable
fun ExerciseItem(exercise: Exercise, onClick: () -> Unit, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)) {
            AsyncImage(
                model = exercise.exerciseIllustration,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = modifier.width(8.dp))
            Column(modifier = modifier.padding(vertical = 16.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    text = exercise.exerciseName,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp
                )
                Text(
                    text = "${exercise.repetitions} Reps",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
