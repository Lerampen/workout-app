package com.example.workoutapp.screens.nutrition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.screens.home.StatItem
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSectionNutr(navController: NavController,modifier: Modifier = Modifier) {
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
                    text = " NUTRITION ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Back arrow"
            )
        } },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopNutrPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        TopSectionNutr(navController = navController)
    }
}

@Composable
fun CalorieCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                StatItem(
                    label = "Calories",
                    progress = 0.75f,
                    icon = Icons.Outlined.Restaurant,
                    value = "1200 cal"
                )
            }
            ProgressNutrients(proteinGrams = 60, carbsGrams = 50, fatGrams = 40)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CaloriesCardPreview() {
    WorkoutAppTheme {
        CalorieCard()
    }
}

@Composable
fun ProgressNutrients(modifier: Modifier = Modifier, proteinGrams : Int, carbsGrams : Int, fatGrams : Int) {

    Column (modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)){
        // TODO: show progress bar indicator for protein
        NutrientsProgressBar(
            label = "Protein",
            progress = 0.5f,
            color = Color.Blue,
            grams = proteinGrams
        )
        // TODO: show progress bar indicator for carbohydrates
        NutrientsProgressBar(
            label = "Carbohydrates",
            progress = 0.7f,
            color = Color.Green,
            grams = carbsGrams
        )
        // TODO: show progress bar indicator for fat
        NutrientsProgressBar(
            label = "Fat",
            progress = 0.3f,
            color = Color.Magenta,
            grams = fatGrams
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressNutrientsPreview() {
    WorkoutAppTheme {
        ProgressNutrients(proteinGrams = 60, carbsGrams = 150, fatGrams = 40)
    }
}

@Composable
fun NutrientsProgressBar(
    modifier: Modifier = Modifier,
    label: String,
    progress: Float,
    color: Color,
    grams: Int
) {
    Column (modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ){
       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ) {
            Text(
                text = label,
                fontFamily = robotoFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = "$grams g",
                fontFamily = robotoFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        LinearProgressIndicator(
            progress = { progress }, modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(
                    RoundedCornerShape(14.dp)
                ),
            color = color,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round

        )
    }
}