package com.example.workoutapp.screens.admin

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.google.firebase.firestore.auth.User

// Example admin screen implementation
@Composable
fun AdminScreen() {
    // Mock data or actual implementation
    val users = remember { mutableStateListOf<User>() }
    val contentItems = remember { mutableStateListOf<ContentItem>() }
    val selectedTab = remember { mutableStateOf(0) } // For switching tabs




    // Display content based on selected tab
        when (selectedTab.value) {
            0 -> {
                // Example: Dashboard
                // Display key metrics or summary
                Text("Dashboard Content")
            }
            1 -> {
                // Example: User Management
                UserManagementScreen(users = users)
            }
            2 -> {
                // Example: Content Management
                ContentManagementScreen(contentItems = contentItems)
            }
            3 -> {
                // Example: Analytics
                AnalyticsScreen()
            }
            4 -> {
                // Example: Settings
                SettingsScreen()
            }
        }

}
@Composable
fun ActivityCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Waves Of Food", style = MaterialTheme.typography.headlineSmall)
            Row(modifier = Modifier.width(IntrinsicSize.Max)) {
                IconButton(onClick = { /* Add Item button click */ }) {
                    Icon(Icons.Outlined.Add, contentDescription = "Add Item")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "0", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActivityCardPreview() {
    WorkoutAppTheme {
        ActivityCard()
    }
}
@Composable
fun UserManagementScreen(users: List<User>) {
    // Implement UI for user management (CRUD operations)
}

@Composable
fun ContentManagementScreen(contentItems: List<ContentItem>) {
    // Implement UI for content management (CRUD operations)
}

@Composable
fun AnalyticsScreen() {
    // Implement UI for analytics (charts, graphs)
}

@Composable
fun SettingsScreen() {
    // Implement UI for settings (app configuration)
}

@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    WorkoutAppTheme {
        AdminScreen()
    }
}
