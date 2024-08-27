package com.example.workoutapp.screens.nutrition

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.workoutapp.data.NutritionPlanWithMeals
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun NutritionPlanList(
    nutritionPlans: List<NutritionPlanWithMeals>,
    onSelectedPlan: (NutritionPlanWithMeals) -> Unit
) {
    LazyColumn {
        items(nutritionPlans){ plan ->
            NutritionPlanItem(plan = plan , onClick = { onSelectedPlan(plan)})
        }
    }
}

@Composable
fun NutritionPlanItem(
    plan: NutritionPlanWithMeals,
    onClick: () -> Unit
) {
    Card (modifier =  Modifier.
    fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
        ){
        Column(modifier =  Modifier . fillMaxWidth().padding(16.dp)) {
            Text(text = plan.plan.name, fontFamily = robotoFontFamily, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Total Meals: ${plan.meals.size}", fontFamily = robotoFontFamily, style = MaterialTheme.typography.titleMedium)

        }
    }
}
