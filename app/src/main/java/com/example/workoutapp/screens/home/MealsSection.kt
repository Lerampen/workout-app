package com.example.workoutapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.R
import com.example.workoutapp.data.Meal
import com.example.workoutapp.data.MealTypes
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun MealsSection(modifier: Modifier = Modifier) {
    TitleMeals(modifier = modifier)
    MealsCard(meal = sampleMeal() )
}

@Composable
fun TitleMeals(modifier: Modifier = Modifier) {
    Row{
        Text(text = "Meals", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = modifier.width(2.dp))
        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos, contentDescription = "forward arrow")
    }
}

@Preview(showBackground = true)
@Composable
private fun TitlePreview() {
    WorkoutAppTheme {
        TitleMeals()
    }
}

@Composable
fun MealsCard (meal : Meal, modifier: Modifier = Modifier) {
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
            .clickable{/*handle click action*/}
            .padding(horizontal = 8.dp)){
            Image(
               painter = painterResource(id = R.drawable.pexels_ketut_subiyanto_5038833),
                contentDescription = meal.mealName,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),

                )
            Spacer(modifier = modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.Center, modifier = modifier.padding(vertical = 16.dp)) { // TODO: Day
                Text(
                    text = meal.mealName,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${meal.calories} Calories",
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                // TODO: min & Exercise
                Text(
                    text =  "Type: ${meal.type}",
                    fontWeight = FontWeight.Normal,
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp
                )
            }
        }
    }
}
@Composable
fun sampleMeal(): Meal {
    return Meal(
        mealId = 2,
        type = MealTypes.BREAKFAST,
        mealName = "Pancakes",
        calories = 350,
        timestamp = System.currentTimeMillis(),
        imageResourceId = R.drawable.pexels_nicola_barts_7936744,
        planId = 2,
        carbs = 100,
        protein = 80 ,
        fat = 20
    // Sample drawable resource
    )
}

@Preview(showBackground = true)
@Composable
private fun WorkoutCardPreview() {
    WorkoutAppTheme {
        MealsCard(meal = sampleMeal())
    }
}