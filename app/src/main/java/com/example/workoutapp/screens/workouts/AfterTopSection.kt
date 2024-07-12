package com.example.workoutapp.screens.workouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun AfterTopSection(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        TargetWorkouts()
        Spacer(modifier = Modifier.height(12.dp))
        Progressbar()
        Spacer(modifier = Modifier.height(12.dp))
        WeekTitle()
    }
}

@Preview(showBackground = true)
@Composable
private fun AfterTopPreview() {
    WorkoutAppTheme {
        AfterTopSection()
    }
}

@Composable
fun TargetWorkouts(modifier: Modifier = Modifier) {
    
    val targetWorkouts = listOf("Full body", "abs", "Legs", "Chest", "booty", "Arms", "cardio", "HIIT")
   LazyRow {
       items(items = targetWorkouts) { target ->

           Card(modifier = modifier
               .height(50.dp)
               .wrapContentWidth()
               .padding(6.dp),
               shape = RoundedCornerShape(8.dp),
               border = BorderStroke(width = 2.dp, color = Color.Black)
           ) {
               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(horizontal = 8.dp),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(
                       text = target,
                       fontSize = 16.sp,
                       fontWeight = FontWeight.SemiBold,
                       fontFamily = robotoFontFamily
                   )
               }
           }
       }
   }
}

@Preview(showBackground = true)
@Composable
private fun TargetWorkoutsPreview() {
    WorkoutAppTheme {
        TargetWorkouts()
    }
}

@Composable
fun Progressbar(modifier: Modifier = Modifier) {
    Row {
        Text(text = "65%", fontFamily = robotoFontFamily, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        LinearProgressIndicator(
            progress = { 0.65f },
            modifier = modifier
                .padding(4.dp)
                .height(12.dp),
            color = Color.DarkGray,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressPreview() {
    WorkoutAppTheme {
        Progressbar()
    }
}

@Composable
fun WeekTitle(modifier: Modifier = Modifier) {
    Text(
        text = "Week 1",
        fontSize = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun WeekTitlePreview() {
    WorkoutAppTheme {
        WeekTitle()
    }
}