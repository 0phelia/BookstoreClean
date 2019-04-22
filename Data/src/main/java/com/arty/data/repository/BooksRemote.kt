package com.arty.data.repository

import com.arty.data.model.GoogleBookEntity
import com.arty.data.model.NytBookEntity

interface BooksRemote {

    //suspend fun getBooks(): List<BookEntity>
//
    //suspend fun getBookDetails(): BookEntity

    suspend fun getNYTimesBooks(listName: String): List<NytBookEntity>

    suspend fun getGoogleBooks(isbn13: String): List<GoogleBookEntity>?

}