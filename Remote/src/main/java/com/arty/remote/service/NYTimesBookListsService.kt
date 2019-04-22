package com.arty.remote.service

import com.arty.remote.model.nytimes.BookListModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTimesBookListsService {

    @GET("svc/books/v3/lists.json")
    fun getBookListAsync(
        @Query("list-name") listName: String,
        @Query("api-key") apiKey: String
    ): Deferred<BookListModel>

}

