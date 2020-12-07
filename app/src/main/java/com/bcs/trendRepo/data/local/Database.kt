package com.bcs.trendRepo.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bcs.trendRepo.data.entity.TrendingRepoResponse

@androidx.room.Database(
    entities = [TrendingRepoResponse::class], version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {

    abstract fun trendRepoListDao(): TrendingRepoListDao

    companion object {
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database? {
            if (INSTANCE == null) {
                synchronized(Database::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java, "trendRepo.db"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}