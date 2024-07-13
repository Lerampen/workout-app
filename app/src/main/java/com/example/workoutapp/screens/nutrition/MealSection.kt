package com.example.workoutapp.screens.nutrition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun MealsSectionNutrition(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        TitleMeal()
        Spacer(modifier = Modifier.weight(1f))
        AddMealButton(onClick = { /*TODO*/ })

    }
}

@Preview(showBackground = true)
@Composable
private fun MealsSectionPreview() {
    WorkoutAppTheme {
        MealsSectionNutrition()
    }
}

@Composable
fun TitleMeal(modifier: Modifier = Modifier) {
    Text(
        text = "Today's meals",
        modifier = modifier,
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
}


@Composable
fun AddMealButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(onClick =  onClick , modifier = modifier
        .padding(16.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
        Text(text = "Add Meal +", fontFamily = robotoFontFamily, fontSize = 16.sp)
    }
}