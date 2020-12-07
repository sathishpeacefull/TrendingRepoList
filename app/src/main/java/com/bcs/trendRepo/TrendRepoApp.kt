package com.bcs.trendRepo

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.bcs.trendRepo.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TrendRepoApp : MultiDexApplication(), HasAndroidInjector {

    companion object {
        var context: Context? = null
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        context = this.baseContext

        AppInjector.init(this)

    }
}
