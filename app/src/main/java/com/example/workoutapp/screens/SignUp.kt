package com.example.workoutapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutapp.data.Resource
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.SignUpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SignUp(
    modifier: Modifier = Modifier,
    onNavigateToLogin : () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
    ) {

    var fname by remember {
        mutableStateOf("")
    }
    var lname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var  passwordVisible by remember {
        mutableStateOf(false)
    }
    var isAdmin by remember { mutableStateOf(false) }

    val signUpState by signUpViewModel.signInState.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.aligned(Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create to your account",
                fontSize = 28.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.padding(top = 48.dp),
                textAlign = TextAlign.Center


            )

            Spacer(modifier = Modifier.height(80.dp))
            OutlinedTextField(
                value = fname,
                onValueChange = { newFname -> fname = newFname },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp,),
                label = { Text(text = "First name", fontFamily = robotoFontFamily) },
                placeholder = { Text(text = "Enter your first name", fontFamily = robotoFontFamily) },
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
                value = lname,
                onValueChange = { newLname -> lname = newLname },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                label = { Text(text = "Last name", fontFamily = robotoFontFamily) },
                placeholder = { Text(text = "Enter your last name", fontFamily = robotoFontFamily) },
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
                value = email,
                onValueChange = { newEmail -> email = newEmail },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
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
                    .padding(vertical = 8.dp, horizontal = 8.dp),
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
            OutlinedTextField(
                value = password,
                onValueChange = { newPasswd -> password = newPasswd },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                label = { Text(text = "Confirm Password", fontFamily = robotoFontFamily) },
                placeholder = { Text(text = "Enter your password", fontFamily = robotoFontFamily) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(24.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {

                    }
                    Icon(imageVector = image , contentDescription = "" )
                },


            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isAdmin,
                    onCheckedChange = { isAdmin = it }
                )
                Text("Register as Admin")
            }

            Spacer(modifier = modifier.height(4.dp))
            Button(
                onClick = {
//                    save the user details on firebase
                   signUpViewModel.signUpUser(fname,lname,email,password,isAdmin)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Sign Up", fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )

            }

        }

       Column( modifier = Modifier.fillMaxWidth(),
           verticalArrangement = Arrangement.spacedBy(16.dp),
           horizontalAlignment = Alignment.CenterHorizontally) {
            when (signUpState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        color = Color.Black
                    )
                }

                is Resource.Error -> {
                    //show text error message

                    Text(
                        text = (signUpState as Resource.Error).message ?: "Unknown Error",
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = robotoFontFamily
                    )
                }

                is Resource.Success -> {
//                navigate to login screen after successful sign up
                    onNavigateToLogin()

                }
            }
        }

        Text(
            text = "Already have an account? Sign in.",
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .clickable {
                    // TODO: navigate to sign in screen
                    onNavigateToLogin()
                },
            textAlign = TextAlign.Center

        )

    }
}

@Preview(showBackground = true,)
@Composable
private fun SignUpPreview() {
    WorkoutAppTheme {
        SignUp(onNavigateToLogin = {})
    }
}