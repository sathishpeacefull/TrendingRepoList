package com.bcs.trendRepo.data.remote

import androidx.lifecycle.LiveData
import com.bcs.trendRepo.data.entity.TrendingRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    companion object {
        val BASE_URL = "https://private-anon-d3815e4334-githubtrendingapi.apiary-mock.com/"
    }

    @GET("repositories")
    fun getTrendRepoList(
        @Query("language") lang: String,
        @Query("since") since: String,
        @Query("spoken_language_code") spokenLangCode: String
    ): LiveData<ApiResponse<List<TrendingRepoResponse>>>

}