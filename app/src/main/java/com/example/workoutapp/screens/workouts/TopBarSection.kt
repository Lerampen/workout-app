package com.example.workoutapp.screens.workouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Equalizer
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.R
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection(navController: NavController, modifier: Modifier = Modifier) {


    val Grey = Color(0xFF6A6A6D) // Custom grey color

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
                        text = " WORKOUTS ",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }


        },
        modifier = modifier,
        navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "back arrow"
            )
        } },
        actions = {
            Icon(imageVector = Icons.Outlined.Tune, contentDescription = "Notifications")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        TopBarSection(navController = navController)
    }
}

