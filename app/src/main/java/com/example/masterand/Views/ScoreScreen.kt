package com.example.masterand.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.masterand.AppViewModelProvider
import com.example.masterand.Navigation.Screen
import com.example.masterand.ViewModels.ProfileViewModel
import com.example.masterand.ViewModels.ScoreViewModel
import kotlinx.coroutines.launch

@Composable
fun ScoreScreen(
    navController: NavController,
    profileId: String,
    score: String,
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    viewModelScore: ScoreViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LaunchedEffect(profileId != null && profileId.trim() != "") {
        if (profileId != null && profileId.trim() != "") {
            viewModel.getProfileById(profileId.toLong())
        }
        viewModel.score.value = score.toInt()
    }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congratulations",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 45.sp,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        Text(
            text = "Recent score: $score",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 35.sp,
            modifier = Modifier.padding(bottom = 25.dp)
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModelScore.saveScore(viewModel.profileId.value, score.toInt())
                    viewModel.updateProfile()
                    navController.navigate("${Screen.Game.route}/${viewModel.profileId.value}")
                }
            },
            shape = CircleShape
        ) {
            Text(text = "Play again")
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModelScore.saveScore(viewModel.profileId.value, score.toInt())
                    viewModel.updateProfile()
                    navController.navigate("${Screen.Profile.route}/${viewModel.profileId.value}")
                }
            },
            shape = CircleShape,
            modifier = Modifier.padding(top = 16.dp) // Add padding to separate buttons
        ) {
            Text(text = "Back to Profile")
        }
    }
}
@Preview
@Composable
fun ScoreScreenPreview() {
    val navController = rememberNavController()
    val profileId = "1"
    val score = "100"

    ScoreScreen(navController = navController, profileId = profileId, score = score)
}