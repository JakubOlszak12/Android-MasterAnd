package com.example.masterand.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.masterand.Entity.PlayerScoreDao
import com.example.masterand.Entity.Profile
import com.example.masterand.Entity.ProfileDao
import com.example.masterand.Entity.Score
import com.example.masterand.Entity.ScoreDao

@Database(entities = [Profile::class, Score::class], version = 16, exportSchema = false)
@TypeConverters(UriConverter::class)
abstract class MasterAndDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    abstract fun playerScoreDao(): PlayerScoreDao

    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var Instance: MasterAndDatabase? = null
        fun getDatabase(context: Context): MasterAndDatabase {
            return Room.databaseBuilder(
                context,
                MasterAndDatabase::class.java,
                "MasterAndDatabase"
            ).fallbackToDestructiveMigration()
                .build().also { Instance = it }
        }
    }
}