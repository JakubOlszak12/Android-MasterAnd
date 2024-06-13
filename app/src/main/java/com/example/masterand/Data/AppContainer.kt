package com.example.masterand.Data

import android.content.Context
import com.example.masterand.Repo.OfflinePlayerWithScoreRepository
import com.example.masterand.Repo.OfflineProfileRepository
import com.example.masterand.Repo.OfflineScoreRepository
import com.example.masterand.Repo.PlayerWithScoreRepository
import com.example.masterand.Repo.ProfileRepository
import com.example.masterand.Repo.ScoreRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val profileRepository: ProfileRepository
    val playerWithScoreRepository: PlayerWithScoreRepository
    val scoreRepository: ScoreRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val profileRepository: ProfileRepository by lazy {
        OfflineProfileRepository(MasterAndDatabase.getDatabase(context).profileDao())
    }

    override val playerWithScoreRepository: PlayerWithScoreRepository by lazy {
        OfflinePlayerWithScoreRepository(MasterAndDatabase.getDatabase(context).playerScoreDao())
    }

    override val scoreRepository: ScoreRepository by lazy {
        OfflineScoreRepository(MasterAndDatabase.getDatabase(context).scoreDao())
    }
}
