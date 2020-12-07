package com.bcs.trendRepo.ui.TrendingRepoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bcs.trendRepo.data.entity.TrendingRepoResponse
import com.bcs.trendRepo.data.repository.TrendingRepository
import com.bcs.trendRepo.util.Resource
import javax.inject.Inject

class TrendingRepoViewModel @Inject constructor(private val trendingRepository: TrendingRepository) :
    ViewModel() {

    private val reloadTrigger = MutableLiveData<Boolean>()

    private val trendingRepoList: LiveData<Resource<List<TrendingRepoResponse>>> =
        Transformations.switchMap(reloadTrigger) {
            trendingRepository.getTrendRepoList()
        }

    init {
        refresh()
    }

    fun getMyTrendRepoList(): LiveData<Resource<List<TrendingRepoResponse>>> = trendingRepoList

    fun refresh() {
        reloadTrigger.value = true
    }

}


