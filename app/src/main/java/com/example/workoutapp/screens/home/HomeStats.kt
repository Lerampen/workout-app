package com.example.workoutapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun HomeStats(modifier: Modifier = Modifier) {

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

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
                    icon = Icons.Outlined.Restaurant
                )
                Spacer(modifier = Modifier.weight(1f))
                StatItem(
                    label = "Workout",
                    progress = 0.9f,
                    icon = Icons.Outlined.FitnessCenter
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                StatItem(
                    label = "Water",
                    progress = 0.65f,
                    icon = Icons.Outlined.WaterDrop
                )
                Spacer(modifier = Modifier.weight(1f))
                StatItem(
                    label = "Steps",
                    progress = 0.4f,
                    icon = Icons.AutoMirrored.Outlined.DirectionsWalk
                )
            }
        }

    }
}
@Composable
fun StatItem(label: String, progress : Float, icon : ImageVector) {

        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium
            )
            Box(modifier = Modifier.size(72.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                    trackColor = Color.LightGray,
                    strokeWidth = 8.dp,
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }

    }
}

@Composable
fun ActivityTitle(modifier: Modifier = Modifier) {
    Text(text = "Today's activity", fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium )
}
@Preview(showBackground = true)
@Composable
private fun StatsCardPreview() {
    WorkoutAppTheme {
        ActivityTitle()

        HomeStats()
    }
}