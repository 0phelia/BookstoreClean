package com.arty.remote.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NYTimesBookListFactoryServiceFactory {

    fun makeNYTimesBookListsService(isDebug: Boolean): NYTimesBookListsService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug)))
        return makeNYTimesBookListsService(okHttpClient)
    }

    private fun makeNYTimesBookListsService(okHttpClient: OkHttpClient): NYTimesBookListsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(NYTimesBookListsService::class.java)
    }






    fun makeGoogleBooksService(isDebug: Boolean): GoogleBooksService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug))
        return makeGoogleBooksService(okHttpClient)
    }

    private fun makeGoogleBooksService(okHttpClient: OkHttpClient): GoogleBooksService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(GoogleBooksService::class.java)
    }



    // TODO move this shit into DI


    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}