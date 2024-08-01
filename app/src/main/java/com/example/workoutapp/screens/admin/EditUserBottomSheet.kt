package com.example.workoutapp.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutapp.data.User
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import kotlinx.coroutines.DisposableHandle

@Composable
fun EditUserBottomSheet(
    user: User,
    onSaveOnClick: (User) -> Unit,
    onCancel: () -> Unit
) {
    var firstName by remember { mutableStateOf(user.firstName) }
    var lastName by remember { mutableStateOf(user.lastName) }
    var email by remember { mutableStateOf(user.email) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Edit User",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName = it
            }, 
            label = { Text(text = "First Name",  fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)},
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color.Black,
                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(24.dp),
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            label = { Text(text = "Last Name", fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium)},
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color.Black,
                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(24.dp),

        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email", fontFamily = robotoFontFamily, fontWeight = FontWeight.SemiBold)},
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color.Black,

                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(24.dp),

        )
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween){

            Button(
                onClick = { onCancel() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text("Cancel", fontFamily = robotoFontFamily, fontWeight = FontWeight.SemiBold)
            }

        Button(
            onClick = {
            onSaveOnClick(user.copy(firstName, lastName, email)) },
            colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
        ) {
            Text(text = "Save", fontFamily = robotoFontFamily, fontWeight = FontWeight.SemiBold)
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditUserBottomSheetPreview() {
    WorkoutAppTheme {
        EditUserBottomSheet(
            user = User("Sally", "Appleton", "appleton@email.com"),
            onSaveOnClick = {}, onCancel = {})
    }
}