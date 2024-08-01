package com.example.workoutapp.screens.admin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workoutapp.data.User
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.ui.theme.robotoFontFamily
import com.example.workoutapp.viewmodels.UsersMgmtViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementScreen(
    viewModel: UsersMgmtViewModel = hiltViewModel(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var currentUser by remember {
        mutableStateOf<User?>(null)
    }
    
    BackHandler {
        if (isBottomSheetVisible) {
          scope.launch { sheetState.hide() }
              .invokeOnCompletion { isBottomSheetVisible = false }
        } else{
            navController.popBackStack()
        }
    }
    val users by viewModel.users.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchUsersFromFireStore()
    }

    Scaffold (topBar = { TopbarUser(navController = navController) }){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
           Text(
               text = "Users",
               style = MaterialTheme.typography.titleLarge,
               fontFamily = robotoFontFamily,
               modifier = Modifier.padding(bottom = 16.dp)
           )

            UserList(
                users = users,
                onEditClick = { user ->
                    currentUser = user
                    isBottomSheetVisible = true
                },
                onDeleteClick = { user -> viewModel.deleteUser(user) }
            )
            if (isBottomSheetVisible && currentUser != null){
                ModalBottomSheet(onDismissRequest = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { isBottomSheetVisible = false }
                }) {

                    EditUserBottomSheet(
                        user = currentUser!!,
                        onSaveOnClick = { updatedUser ->
                            viewModel.updateUser(updatedUser)
                            scope.launch { sheetState.hide() }
                                .invokeOnCompletion { isBottomSheetVisible = false }
                        },
                        onCancel = {
                            scope.launch { sheetState.hide() }
                                .invokeOnCompletion { isBottomSheetVisible = false }
                        }
                    )

                    }
                }
            }

        }

    }


// TODO: include a top app bar 
@Composable
fun UserList(
    users : List<User>,
    onEditClick: (User) -> Unit,
    onDeleteClick: (User) -> Unit
) {
    LazyColumn {
        items(users){user->
            UserCard(
                user = user,
                onEditClick = {
                    // handle edit user
                    onEditClick(user)
                },
                onDeleteClick = {
//                        Handle delete user
                    onDeleteClick(user)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarUser(modifier: Modifier = Modifier,navController: NavController) {
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
                    text = " User Management ",
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }


        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Back arrow"
                )
            } },
        actions = {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More option")
        }
    )
}

// TODO: a card for the users

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: User,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
   
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onEditClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = robotoFontFamily,
                        fontSize = 16.sp
                    )

                }
                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit",
                            tint = Color.Cyan
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun UserManagementScreenPreview() {
    WorkoutAppTheme {
        val navController = rememberNavController()
        UserManagementScreen(navController = navController)
    }
}

