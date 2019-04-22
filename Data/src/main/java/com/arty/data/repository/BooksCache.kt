package com.arty.data.repository

import com.arty.data.model.BookEntity

interface BooksCache {

    suspend fun clearBooks()

    suspend fun saveToCache(books: List<BookEntity>)

    suspend fun getBooks(): List<BookEntity>

    suspend fun getLikedBooks(): List<BookEntity>

    suspend fun setBookLiked(bookId: String)

    suspend fun setBookUnliked(bookId: String)

    suspend fun areBooksCached(): Boolean

    suspend fun setLastCacheTime(time: Long)

    suspend fun isCacheExpired(): Boolean

    suspend fun getBook(bookId: String): BookEntity
}