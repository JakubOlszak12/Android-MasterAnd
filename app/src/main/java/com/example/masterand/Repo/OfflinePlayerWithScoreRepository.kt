package com.example.masterand.Repo

import com.example.masterand.Entity.PlayerScoreDao
import com.example.masterand.Entity.PlayerWithScore
import kotlinx.coroutines.flow.Flow

class OfflinePlayerWithScoreRepository(private val playerWithScoreDao: PlayerScoreDao) :
    PlayerWithScoreRepository {
    override fun getPlayerWithScores(): Flow<List<PlayerWithScore>> =
        playerWithScoreDao.getUsersWithPlaylists()
}