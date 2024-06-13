package com.example.masterand.Entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val name: String,
    val numberOfColors: Int,
    val profileImageUri: Uri?,
    val score: Int,
)

