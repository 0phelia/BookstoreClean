package com.arty.data

import com.arty.data.mapper.BookMapper
import com.arty.data.repository.BooksCache
import com.arty.data.store.BooksCacheDataStore
import com.arty.data.store.BooksRemoteDataStore
import com.arty.domain.model.Book
import com.arty.domain.repository.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val mapper: BookMapper,
    private val cache: BooksCache,
    private val booksCacheDataStore: BooksCacheDataStore,
    private val booksRemoteDataStore: BooksRemoteDataStore
): BooksRepository {

    override suspend fun getBookDetails(bookId: String): Book {
        val book = booksCacheDataStore.getBookDetails(bookId)
        return mapper.mapFromEntity(book)
    }

    override suspend fun getLikedBooks(): List<Book> {
        return booksCacheDataStore.getLikedBooks().map { book -> mapper.mapFromEntity(book) }
    }

    override suspend fun likeBook(bookId: String) {
        booksCacheDataStore.setBookLiked(bookId)
    }

    override suspend fun unlikeBook(bookId: String) {
        booksCacheDataStore.setBookUnliked(bookId)
    }

    override suspend fun getBooks(): List<Book>{
        return if (cache.areBooksCached() && !cache.isCacheExpired()) {
            cache.getBooks().map { book -> mapper.mapFromEntity(book) }
        } else {
            val books = booksRemoteDataStore.getBooks()
            booksCacheDataStore.saveBooks(books)
            books.map { book -> mapper.mapFromEntity(book) }
        }
    }

}