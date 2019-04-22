package com.arty.data.store

import com.arty.data.model.BookEntity
import com.arty.data.repository.BooksCache
import com.arty.data.repository.BooksDataStore
import javax.inject.Inject

open class BooksCacheDataStore @Inject constructor(
    private val booksCache: BooksCache
): BooksDataStore {
    override suspend fun getBookDetails(bookId: String): BookEntity {
        return booksCache.getBook(bookId)
    }

    override suspend fun getBooks(): List<BookEntity> {
        return booksCache.getBooks()
    }

    override suspend fun saveBooks(books: List<BookEntity>) {
        booksCache.saveToCache(books)
        booksCache.setLastCacheTime(System.currentTimeMillis())
    }

    override suspend fun clearBooks() {
        booksCache.clearBooks()
    }

    override suspend fun getLikedBooks(): List<BookEntity> {
        return booksCache.getLikedBooks()
    }

    override suspend fun setBookLiked(bookId: String) {
        booksCache.setBookLiked(bookId)
    }

    override suspend fun setBookUnliked(bookId: String) {
        booksCache.setBookUnliked(bookId)
    }

}