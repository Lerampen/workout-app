package com.example.workoutapp.screens.workouts

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.workoutapp.R
import com.example.workoutapp.navigation.Screens
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.WorkoutManagementViewModel
import kotlin.time.Duration


@Composable
fun WorkoutList(
    navController: NavController,
    viewModel: WorkoutManagementViewModel = hiltViewModel(),

) {
    val workouts by viewModel.workouts.collectAsState(emptyList())

    LazyColumn (modifier = Modifier.fillMaxSize()){
        items(workouts){ workout->
            ExerciseCard(
                day = "Day ${workout.day}",
                duration = workout.duration,
                exerciseCount = workout.exerciseCount,
                imageUrl = workout.imageUrl,
                onClick = {
                    navController.navigate(Screens.ExerciseList.route.replace("{day}", workout.day))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WorkoutListPreview() {
    WorkoutAppTheme {
        WorkoutList(navController = rememberNavController())
    }
}

@Composable
fun ExerciseCard(
    day: String,
    duration: Int,
    exerciseCount: Int,
    imageUrl: String,
    onClick: () -> Unit,

) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick) // make the Card clickable
    ) {

        // TODO: Image
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Workout Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp)),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(vertical = 16.dp)) { // TODO: Day
                Text(
                    text = day,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp
                )
//                Text(
//                    text = "Duration: $duration minutes",
//                    style = MaterialTheme.typography.bodyMedium
//                )
                // TODO: min & Exercise
                Text(
                    text = "$duration Mins • $exerciseCount Exercises",
                    fontFamily = robotoFontFamily,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
