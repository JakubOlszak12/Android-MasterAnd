package com.example.masterand.Entity

import androidx.room.Embedded
import androidx.room.Relation

data class PlayerWithScore(
    @Embedded val profile: Profile,
    @Relation(
        parentColumn = "id",
        entityColumn = "playerId"
    )
    val scores: List<Score>
)