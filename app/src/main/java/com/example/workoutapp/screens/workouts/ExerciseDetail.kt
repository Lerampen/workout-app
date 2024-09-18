package com.example.workoutapp.screens.workouts

import android.media.MediaPlayer
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.workoutapp.R
import com.example.workoutapp.data.Exercise
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.ExerciseViewModel
import kotlinx.coroutines.delay

@Composable
fun ExerciseDetail(
    exerciseId: Int,
    navController: NavController,
    viewModel: ExerciseViewModel = hiltViewModel()
) {
    val exercise by viewModel.exercise.collectAsState()

    LaunchedEffect(exerciseId) {
        Log.d("ExerciseDetail", "Fetching exercise with ID: $exerciseId")
        viewModel.fetchExerciseById(exerciseId)
    }
    BackHandler {
        navController.popBackStack()
    }

//    Timer State
    var timeLeft by remember {
        mutableIntStateOf(60)
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
        timeLeft = 60 // reset timer if needed
    }

//    Play Audio function
    fun playAudio(resourceId : Int){
        val mediaPlayer = MediaPlayer.create(context, resourceId)
    mediaPlayer.start()
    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release()
    }
    }

    //        Timer Logic inside LaunchedEffect
    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--

                if (timeLeft == 4) {
                    playAudio(R.raw.countdown_sound_effect)
                }
            }
            isTimerRunning = false // Stop the timer when it reaches 0

        }


    }


Scaffold(
    topBar = { TopbarExercise(navController = navController)},
    content = {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


                 AsyncImage(
                     model = exercise?.exerciseIllustration ?: "",
                     contentDescription = exercise?.exerciseName,
                     contentScale = ContentScale.Fit,
                     modifier = Modifier
                         .size(200.dp)
                         .clip(RoundedCornerShape(8.dp))
                 )

                 Text(
                     text = "Exercise : ${exercise?.exerciseName ?: "Loading ..."}",
                     fontSize = 24.sp,
                     fontWeight = FontWeight.Medium
                 )
                 Text(
                     text = " Reps: ${exercise?.repetitions} ",
                     fontSize = 24.sp,
                     fontWeight = FontWeight.Medium
                 )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Time left: $timeLeft seconds", fontSize = 20.sp)

            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (!isTimerRunning) {
                            startTimer()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green
                    )
                ) {
                    Text(
                        text = "Start",
                        fontFamily = robotoFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { stopTimer() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(
                        text = "Stop",
                        fontFamily = robotoFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }


//        other exercise detail
        }
    }
)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarExercise(navController: NavController) {
    TopAppBar(
        title = {

            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Exercises",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Back arrow"
                )
            }
        },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}



fun getExerciseById(exerciseId: String): Exercise {
    return Exercise(
        exerciseName = "Squats",
        exerciseIllustration = "https://www.pexels.com/photo/woman-doing-squats-5038833/",
        repetitions = 10,
        workoutId = 1,
        workoutDay = "Day 1",
        id = 1
    )
}
