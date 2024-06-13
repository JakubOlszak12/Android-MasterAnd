package com.example.masterand.Views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.masterand.AppViewModelProvider
import com.example.masterand.GameScreen.FeedbackCircles
import com.example.masterand.GameScreen.SelectableColorsRow
import com.example.masterand.GameScreen.checkColors
import com.example.masterand.GameScreen.selectNextAvailableColor
import com.example.masterand.GameScreen.selectRandomColors
import com.example.masterand.Navigation.Screen
import com.example.masterand.ViewModels.ProfileViewModel
import com.example.masterand.ViewModels.ScoreViewModel
import kotlinx.coroutines.launch

data class GameRowData(
    var chosenColors: MutableList<Color> = mutableStateListOf(
        Color.White,
        Color.White,
        Color.White,
        Color.White
    ),
    var feedbackColors: MutableList<Color> = mutableStateListOf(
        Color.White,
        Color.White,
        Color.White,
        Color.White
    )
)


@Composable
fun GameScreen(
    navController: NavController,
    profileId: String,
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    viewModel2: ScoreViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    var attempts by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }
    val colors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow,
        Color.Magenta, Color.Cyan, Color.LightGray, Color.Gray,
        Color.DarkGray, Color.Black
    )

    var availableColors by remember {
        mutableStateOf(colors.shuffled().take(viewModel.numberOfColors.value))
    }
    var correctColors by remember {
        mutableStateOf(selectRandomColors(availableColors))
    }
    var gameRows by remember {
        mutableStateOf(mutableStateListOf(GameRowData()))
    }

    LaunchedEffect(profileId) {
        if (profileId.isNotBlank()) {
            viewModel.getProfileById(profileId.toLong())
            availableColors = colors.shuffled().take(viewModel.numberOfColors.value)
            correctColors = selectRandomColors(availableColors)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (gameOver) {
                            viewModel2.saveScore(viewModel.profileId.value, attempts)
                        }
                        if (gameOver && attempts > viewModel.score.value) {
                            viewModel.score.value = attempts
                            viewModel.updateScore()
                        }
                        navController.navigate("${Screen.Profile.route}/${viewModel.profileId.value}")
                    }
                },
                shape = CircleShape,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Back")
            }
        }

        Text(text = "Your score: $attempts", fontSize = 42.sp)

        gameRows.forEachIndexed { index, rowData ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                SelectableColorsRow(
                    colors = rowData.chosenColors,
                    onClick = { buttonIndex ->
                        if (index == gameRows.lastIndex && !gameOver) {
                            val newColor = selectNextAvailableColor(
                                availableColors,
                                rowData.chosenColors,
                                buttonIndex
                            )
                            rowData.chosenColors[buttonIndex] = newColor
                        }
                    }
                )
                FeedbackCircles(colors = rowData.feedbackColors)
            }

            if (index == gameRows.lastIndex && !gameOver) {
                Button(
                    onClick = {
                        val newFeedbackColors = checkColors(
                            rowData.chosenColors.toList(),
                            correctColors,
                            Color.White
                        )
                        rowData.feedbackColors = newFeedbackColors
                        attempts++
                        if (rowData.feedbackColors.all { it == Color.Red }) {
                            gameOver = true
                        } else {
                            gameRows.add(GameRowData())
                        }
                    },
                    shape = CircleShape
                ) {
                    Text(text = "Check")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (gameOver) {
            Button(
                onClick = {
                    navController.navigate("${Screen.Score.route}/${viewModel.profileId.value}/$attempts")
                },
                shape = CircleShape
            ) {
                Text(text = "Continue")
            }
        }
    }
}