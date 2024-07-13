package com.example.workoutapp.screens.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSectionPay(modifier: Modifier = Modifier) {
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
                    text = " PAYMENTS ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
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
private fun TopSectionPayPreview() {
    WorkoutAppTheme {
        TopSectionPay()
    }
}