package com.example.workoutapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.R
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSection(modifier: Modifier = Modifier) {
    TopAppBar(
        // TODO:  Text(text = "Home Screen" , textAlign = TextAlign.Center)
        title = {
            Row(verticalAlignment =  Alignment.CenterVertically) {

                IconButton(onClick = { /*onProfileClick*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.pexels_anastasiia_chaikovska_206547003_12210003),
                        contentDescription = "profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(56.dp)
                            .clip(shape = CircleShape)

                    )
                }
                Spacer(modifier = modifier.width(16.dp))
                Column(modifier = modifier.padding(vertical = 4.dp)){
                    Text(
                        text = "Welcome",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                    Text(
                        text = " Stacy Apeyon \uD83D\uDC4B ",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }

            }
        },
        modifier = modifier,
        actions = {
            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "Notifications")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopSectionPreview() {

    WorkoutAppTheme {
        TopSection()
    }
}