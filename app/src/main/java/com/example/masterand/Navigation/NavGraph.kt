package com.example.masterand.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.masterand.Views.GameScreen
import com.example.masterand.Views.HighScoresScreen
import com.example.masterand.Views.ProfileCard
import com.example.masterand.Views.ScoreScreen
import com.example.masterand.Views.StartScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable(
            route = Screen.Start.route + "?profileId={profileId}",
            arguments = listOf(navArgument("profileId") { defaultValue = "" })
        ) { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId")
            StartScreen(profileId = profileId, navController = navController)
        }

        composable(
            route = Screen.Profile.route + "/{profileId}"
        ) { backStackEntry ->
            ProfileCard(
                profileId = backStackEntry.arguments?.getString("profileId") ?: "",
                navController = navController
            )
        }

        composable(
            route = Screen.Game.route + "/{profileId}"
        ) { backStackEntry ->
            GameScreen(
                navController = navController,
                profileId = backStackEntry.arguments?.getString("profileId") ?: ""
            )
        }

        composable(
            route = Screen.Score.route + "/{profileId}/{score}"
        ) { backStackEntry ->
            ScoreScreen(
                navController = navController,
                profileId = backStackEntry.arguments?.getString("profileId") ?: "",
                score = backStackEntry.arguments?.getString("score") ?: "0"
            )
        }

        composable(
            route = Screen.HighScores.route + "/{profileId}"
        ) { backStackEntry ->
            HighScoresScreen(
                navController = navController,
                profileId = backStackEntry.arguments?.getString("profileId") ?: ""
            )
        }
    }
}