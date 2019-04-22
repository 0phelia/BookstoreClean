package com.arty.data.store

import com.arty.data.mapper.GoogleAndNytBookMerger
import com.arty.data.model.BookEntity
import com.arty.data.repository.BooksDataStore
import com.arty.data.repository.BooksRemote
import javax.inject.Inject

open class BooksRemoteDataStore @Inject constructor(
    private val booksRemote: BooksRemote,
    private val booksMerger: GoogleAndNytBookMerger
) : BooksDataStore {
    override suspend fun getBookDetails(bookId: String): BookEntity {
        throw UnsupportedOperationException("Should be called from cache only...")
    }

    // TODO pass "hardcover-fiction" as argument
    // TODO what if we fetched multiple books from Googe API or none?
    override suspend fun getBooks(): List<BookEntity> {
        val nytBooks = booksRemote.getNYTimesBooks("hardcover-fiction")
        return nytBooks.map { nytBook ->
            val googleBook = booksRemote.getGoogleBooks(nytBook.isbn13)
            booksMerger.merge(googleBook?.first(), nytBook)
        }
    }

    override suspend fun saveBooks(books: List<BookEntity>) {
        throw UnsupportedOperationException("Saving books isn't supported here...")
    }

    override suspend fun clearBooks() {
        throw UnsupportedOperationException("Clearing books isn't supported here...")
    }

    override suspend fun getLikedBooks(): List<BookEntity> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override suspend fun setBookLiked(bookId: String) {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override suspend fun setBookUnliked(bookId: String) {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

}