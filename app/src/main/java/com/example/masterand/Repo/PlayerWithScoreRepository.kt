package com.example.masterand.Repo

import com.example.masterand.Entity.PlayerWithScore
import kotlinx.coroutines.flow.Flow

interface PlayerWithScoreRepository {
    fun getPlayerWithScores(): Flow<List<PlayerWithScore>>

}