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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.workoutapp.R
import com.example.workoutapp.navigation.Screens
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun ExerciseList(day :String, navController: NavController,modifier: Modifier = Modifier) {
    val exercises = listOf(
        Exercise("Exercise 1", R.drawable.pexels_ketut_subiyanto_5038833
            , 10),
        Exercise("Exercise 1", R.drawable.pexels_mastercowley_1300526, 10)

    )

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(exercises){ exercise ->
            ExerciseItem(exercise = exercise, onClick = {
                navController.navigate("exercise_detail/${exercise.id}")
            }, modifier = Modifier)
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
            Image(
                painter = painterResource(id = exercise.exerciseIllustration),
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
