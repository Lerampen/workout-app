package com.example.workoutapp.screens.workouts

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.R
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily


@Composable
fun WorkoutList(modifier: Modifier = Modifier) {
    val workouts = listOf("Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7", "Day 8", "Day 9", "Day 10" )

    LazyColumn (modifier = modifier.fillMaxSize()){
        items(workouts){ day->
            ExerciseCard(day)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WorkoutListPreview() {
    WorkoutAppTheme {
        WorkoutList()
    }
}

@Composable
fun ExerciseCard(day: String, modifier: Modifier = Modifier) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        // TODO: Image
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            Image(
                painter = painterResource(id = R.drawable.pexels_ketut_subiyanto_5038833),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp)),

                )
            Spacer(modifier = modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.Center, modifier = modifier.padding(vertical = 16.dp)) { // TODO: Day
                Text(
                    text = day,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp
                )
                // TODO: min & Exercise
                Text(
                    text = "10 Min . 10 Exercise",
                    fontWeight = FontWeight.Normal,
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp
                )
            }
        }
    }
}
