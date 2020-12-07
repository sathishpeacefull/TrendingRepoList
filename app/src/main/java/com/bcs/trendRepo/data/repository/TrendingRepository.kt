package com.bcs.trendRepo.data.repository

import com.bcs.trendRepo.data.entity.TrendingRepoResponse
import com.bcs.trendRepo.data.remote.InternetBoundResource
import com.bcs.trendRepo.data.remote.WebService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingRepository @Inject constructor(
    private val webService: WebService
) {
    fun getTrendRepoList() = object : InternetBoundResource<List<TrendingRepoResponse>>() {
        override fun createCall() = webService.getTrendRepoList("", "daily", "")
    }.asLiveData()
}