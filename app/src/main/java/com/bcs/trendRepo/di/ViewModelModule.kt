package com.bcs.trendRepo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bcs.trendRepo.ui.AppViewModelFactory
import com.bcs.trendRepo.ui.TrendingRepoList.TrendingRepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TrendingRepoViewModel::class)
    abstract fun bindTrendingRepoViewModel(viewModel: TrendingRepoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
