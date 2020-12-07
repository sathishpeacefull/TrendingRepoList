package com.bcs.trendRepo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bcs.trendRepo.data.entity.TrendingRepoResponse

@Dao
interface TrendingRepoListDao {
    // Query to retrieve data
    @Query("SELECT * FROM trendRepoList")
    fun getTrendRepoList(): List<TrendingRepoResponse>

    //Query to insert item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrendRepoList(trendingRepoResponse: TrendingRepoResponse)

    //Query to delete data
    @Query("DELETE FROM trendRepoList")
    fun deleteTable()
}