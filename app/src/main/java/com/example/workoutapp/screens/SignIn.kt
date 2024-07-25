package com.example.workoutapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.data.Resource
import com.example.workoutapp.navigation.Screens
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.LogInViewModel

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    onNavigateToSignUp: () -> Unit,
    navController: NavController,
    logInViewModel: LogInViewModel = hiltViewModel()
) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    var isAdmin by remember { mutableStateOf(false) }
    val loginState by logInViewModel.loginState.collectAsState()
    val context  = LocalContext.current
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
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
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
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {

                    }
                    Icon(imageVector = image , contentDescription = "" )
                },


            )

            Spacer(modifier = modifier.height(16.dp))
            Button(
                onClick = { logInViewModel.loginUser(email, password) },
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

        when (loginState) {
            is Resource.Loading ->{
//                show a loading indicator
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            is Resource.Success ->{

//                You need to check if the logged-in user is an admin and navigate accordingly

                val user = (loginState as Resource.Success).data?.user
                if (user != null) {
                    LaunchedEffect(user) {
                         isAdmin = logInViewModel.checkIfAdmin(user.email)
                        if (isAdmin) {
                            navController.navigate(Screens.AdminDashboard.route)
                        } else {
                            navController.navigate(Screens.Home.route)
                        }
                    }
                }
            }
            is Resource.Error ->{
                Toast.makeText(context, "Login Failed: ${(loginState as Resource.Error).message}", Toast.LENGTH_SHORT).show()
            }
        }
        if (!isAdmin) { // Only show loading indicator if admin status isn't yet determined
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        Text(
            text = "Don't have an account? Sign up.",
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .clickable {
                    // TODO: navigate to sign up screen
                    onNavigateToSignUp()
                },
            textAlign = TextAlign.Center

            )

    }

}

@Preview(showBackground = true, apiLevel = 34)
@Composable
private fun SignInPreview() {
    WorkoutAppTheme {
        val navController  = rememberNavController()
        SignIn(onNavigateToSignUp = {}, navController = navController)
    }
}