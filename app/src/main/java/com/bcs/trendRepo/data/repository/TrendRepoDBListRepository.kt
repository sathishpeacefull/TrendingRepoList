package com.bcs.trendRepo.data.repository

import android.content.Context
import com.bcs.trendRepo.data.entity.TrendingRepoResponse
import com.bcs.trendRepo.data.local.Database
import com.bcs.trendRepo.data.local.TrendingRepoListDao
import javax.inject.Singleton

@Singleton
class TrendRepoDBListRepository(
    context: Context
) {
    var db: TrendingRepoListDao = Database.getInstance(context)?.trendRepoListDao()!!

    fun getAllData(): List<TrendingRepoResponse> {
        return db.getTrendRepoList()
    }

    fun insertData(data: TrendingRepoResponse) {
        db.insertTrendRepoList(data)
    }

    fun deleteAll() {
        db.deleteTable()
    }

}