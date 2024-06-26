package com.example.masterand.Views

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.masterand.AppViewModelProvider
import com.example.masterand.Navigation.Screen
import com.example.masterand.ViewModels.ProfileViewModel

@Composable
fun ProfileCard(
    profileId: String,
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LaunchedEffect(true) {
        viewModel.getProfileById(profileId.toLong())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top

    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("${Screen.Start.route}?profileId=${profileId}")
                },
                shape = CircleShape,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Back")
            }
            Button(
                onClick = {
                    navController.navigate(route = Screen.Start.route)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(100.dp),
                shape = CircleShape
            ) {
                Text(text = "Logout")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            Column {
                AsyncImage(
                    model = viewModel.profileImageUri.value,
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(120.dp),
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = viewModel.name.value,
                    style = TextStyle(fontSize = 24.sp)
                )
                Text(text = "Email: " + viewModel.email.value, style = TextStyle(fontSize = 16.sp))
                Text(
                    text = "Colors: " + viewModel.numberOfColors.value,
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "Highest score: " + viewModel.score.value,
                    style = TextStyle(fontSize = 16.sp)
                )
            }



        }
        Spacer(modifier = Modifier.weight(1f))
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = {
                    navController.navigate("${Screen.Game.route}/${viewModel.profileId.value}")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(130.dp)
            ) {
                Text(text = "Play")
            }
            Button(
                onClick = {
                    navController.navigate("${Screen.HighScores.route}/${viewModel.profileId.value}")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(130.dp)
            ) {
                Text(text = "Scores")
            }

        }
    }
}

