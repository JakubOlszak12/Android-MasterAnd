package com.example.masterand.Repo

import com.example.masterand.Entity.Score

interface ScoreRepository {
    suspend fun insertScore(score: Score): Long
}