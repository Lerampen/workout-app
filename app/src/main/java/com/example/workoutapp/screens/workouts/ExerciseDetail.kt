package com.example.workoutapp.screens.workouts

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.R
import com.example.workoutapp.ui.theme.robotoFontFamily
import kotlinx.coroutines.delay

@Composable
fun ExerciseDetail(exerciseId : String,modifier: Modifier = Modifier) {
    val exercise = getExerciseById(exerciseId)

//    Timer State
    var timeLeft by remember {
        mutableIntStateOf(30)
    }
//    Manage Timer State: Use a state variable to track whether the timer is running.
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

//    Function to start timer
    fun startTimer() {

            isTimerRunning = true
        }
    fun stopTimer(){
        isTimerRunning = false
        timeLeft = 30 // reset timer if needed
    }

//    Play Audio function
    fun playAudio(resourceId : Int){
        val mediaPlayer = MediaPlayer.create(context, resourceId)
    mediaPlayer.start()
    }

    //        Timer Logic inside LaunchedEffect
    LaunchedEffect(isTimerRunning) {
        while(timeLeft > 0){
            if (timeLeft == 4){
                playAudio(R.raw.countdown_sound_effect)
            }
            delay(1000L)
            timeLeft--
        }


    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Exercise : ${exercise.exerciseName}", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Time left: $timeLeft seconds", fontSize = 20.sp)

        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    if (!isTimerRunning){
                    startTimer()
                }
                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                )
            ) {
                Text(text = "Start", fontFamily = robotoFontFamily, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { stopTimer() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ){
                Text(text = "Stop", fontFamily = robotoFontFamily, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }

        // TODO: add a start button  / when clicked START THE TIMER
        // TODO: add a stop button
        // TODO: add an image for the illustration of the exercise
        // TODO: add a Circular indicator to show progress of the exercises / time left
//        other exercise detail
    }

}

fun getExerciseById(exerciseId: String): Exercise{
    return Exercise("Exercise 1", R.drawable.pexels_mastercowley_1300526, 10, id = exerciseId)
}
