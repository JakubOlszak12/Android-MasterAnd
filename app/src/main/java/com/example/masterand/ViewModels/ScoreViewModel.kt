package com.example.masterand.ViewModels

import androidx.lifecycle.ViewModel
import com.example.masterand.Entity.Score
import com.example.masterand.Repo.ScoreRepository

class ScoreViewModel(private val scoreRepository: ScoreRepository) : ViewModel() {
    suspend fun saveScore(id: Long, score: Int) {
        scoreRepository.insertScore(Score(playerId = id, score = score))
    }
}