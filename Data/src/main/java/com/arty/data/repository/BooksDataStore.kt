package com.arty.data.repository

import com.arty.data.model.BookEntity

interface BooksDataStore {

    suspend fun getBooks(): List<BookEntity>

    suspend fun getBookDetails(bookId: String): BookEntity

    suspend fun saveBooks(books: List<BookEntity>)

    suspend fun clearBooks()

    suspend fun getLikedBooks(): List<BookEntity>

    suspend fun setBookLiked(bookId: String)

    suspend fun setBookUnliked(bookId: String)
}