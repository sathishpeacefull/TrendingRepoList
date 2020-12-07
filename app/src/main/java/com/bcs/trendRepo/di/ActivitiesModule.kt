package com.bcs.trendRepo.di

import com.bcs.trendRepo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeActivityAndroidInjector(): MainActivity
}
