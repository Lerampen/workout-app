package com.example.workoutapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@Composable
fun SignIn(modifier: Modifier = Modifier) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.aligned(Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login to your account",
                fontSize = 28.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.padding(top = 48.dp),
                textAlign = TextAlign.Center


            )

            Spacer(modifier = Modifier.height(200.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { newEmail -> email = newEmail },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                label = { Text(text = "Email", fontFamily = robotoFontFamily) },
                placeholder = { Text(text = "example@example.com", fontFamily = robotoFontFamily) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(24.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )


            )
            OutlinedTextField(
                value = password,
                onValueChange = { newPasswd -> password = newPasswd },
                modifier = modifier.fillMaxWidth().padding( horizontal = 8.dp),
                label = { Text(text = "Password", fontFamily = robotoFontFamily) },
                placeholder = { Text(text = "Enter your password", fontFamily = robotoFontFamily) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(24.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )


            )

            Spacer(modifier = modifier.height(16.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Sign In", fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,

                )

            }

        }


        Text(
            text = "Don't have an account? Sign up.",
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp).clickable {
                // TODO: navigate to sign up screen
            },
            textAlign = TextAlign.Center

            )

    }

}

@Preview(showBackground = true, apiLevel = 34)
@Composable
private fun SignInPreview() {
    WorkoutAppTheme {
        SignIn()
    }
}