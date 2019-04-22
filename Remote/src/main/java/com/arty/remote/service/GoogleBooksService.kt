package com.arty.remote.service

import com.arty.remote.model.google.GoogleBooksResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksService {
    @GET("books/v1/volumes")
    fun getBookDetailsAsync(
        @Query("q") q: String,
        @Query("key") apiKey: String
    ): Deferred<GoogleBooksResponseModel>
}