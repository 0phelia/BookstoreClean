package com.arty.domain.repository

import com.arty.domain.model.Book

interface BooksRepository {

    suspend fun getBooks(): List<Book>

    suspend fun getBookDetails(bookId: String): Book

    suspend fun getLikedBooks(): List<Book>

    suspend fun likeBook(bookId: String)

    suspend fun unlikeBook(bookId: String)

}