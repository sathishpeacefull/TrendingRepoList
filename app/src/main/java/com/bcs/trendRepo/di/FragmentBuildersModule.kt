package com.bcs.trendRepo.di

import com.bcs.trendRepo.ui.Search.SearchFrag
import com.bcs.trendRepo.ui.TrendingRepoList.TrendingRepoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    // contribute trending repositories list fragment
    @ContributesAndroidInjector
    abstract fun contributeTrendingRepoListFragment(): TrendingRepoListFragment

    // contribute search fragment
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFrag

}
