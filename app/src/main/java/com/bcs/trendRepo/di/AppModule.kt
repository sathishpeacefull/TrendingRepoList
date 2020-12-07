package com.bcs.trendRepo.di

import com.bcs.trendRepo.data.remote.WebService
import com.bcs.trendRepo.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])

class AppModule {
    @Singleton
    @Provides
    fun provideWebService(): WebService {

        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(80, TimeUnit.SECONDS)

        // add header and method as last interceptor
        httpClient.addInterceptor { chain ->
            var request = chain.request()

            request = request.newBuilder()
                .header("Content-Type", "application/json")
                .method(chain.request().method, chain.request().body)
                .build()

            chain.proceed(request)
        }

        // add logging as last interceptor
        httpClient.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl(WebService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(httpClient.build())
            .build()
            .create(WebService::class.java)
    }

}
