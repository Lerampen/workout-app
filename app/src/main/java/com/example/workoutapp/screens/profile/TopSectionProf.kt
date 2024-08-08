package com.example.workoutapp.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.R
import com.example.workoutapp.data.User
import com.example.workoutapp.navigation.Screens
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.ProfileViewModel
import com.example.workoutapp.viewmodels.UsersMgmtViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSectionProf(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

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
                    text = " PROFILE ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()}) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "back arrow"
            )
        } },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopSectionProfPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        TopSectionProf(navController = navController)
    }
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    currentUser: User?
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.pexels_anastasiia_chaikovska_206547003_12210003),
                contentDescription = "profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${currentUser?.firstName ?: "Guest"} ${currentUser?.lastName}",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "${currentUser?.email}",
                    fontFamily = robotoFontFamily,
                    fontSize = 16.sp,
                   fontWeight = FontWeight.Medium
                )
            }
        }

}

@Preview(showBackground = true)
@Composable
private fun ProfileImagePreview() {
    WorkoutAppTheme {
        ProfileImage(currentUser = User(firstName = "Joe", lastName = "Doe", email = "joedoe@email.com"))
    }
}

@Composable
fun MidSection(modifier: Modifier = Modifier , navController: NavController, profileViewModel: ProfileViewModel ) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color.LightGray,
            content = {}
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountBalanceWallet,
                contentDescription = "Wallet Icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Account Details",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = "Person Icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Personal Details",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.PersonalDetails.route)

                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Download,
                contentDescription = "Download Icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Export Data",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings Icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Settings",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Security,
                contentDescription = "Security Icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Data and Privacy",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,

            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Logout,
                contentDescription = "Logout Icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Logout",
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                      profileViewModel.signOut()
                    Toast.makeText(context, "Logged Out Successfully!", Toast.LENGTH_SHORT).show()
                    navController.navigate("login_screen") // Update with your login screen route
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MidSectionPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        val viewModel = hiltViewModel<ProfileViewModel>()
        MidSection(navController = navController, profileViewModel = viewModel)
    }
}