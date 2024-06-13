package com.example.masterand.Repo

import com.example.masterand.Entity.Score
import com.example.masterand.Entity.ScoreDao

class OfflineScoreRepository(private val scoreDao: ScoreDao) : ScoreRepository {
    override suspend fun insertScore(score: Score): Long = scoreDao.insert(score)
}