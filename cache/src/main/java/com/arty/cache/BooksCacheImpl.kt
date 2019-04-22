package com.arty.cache

import com.arty.cache.db.BookDatabase
import com.arty.cache.mapper.Mapper
import com.arty.cache.model.BookModelBundle
import com.arty.cache.model.BookQueryResult
import com.arty.cache.model.Config
import com.arty.data.model.BookEntity
import com.arty.data.repository.BooksCache
import java.util.Arrays.asList
import javax.inject.Inject

class BooksCacheImpl @Inject constructor(
    private val booksDatabase: BookDatabase,
    private val mapper: Mapper<BookQueryResult, BookEntity, BookModelBundle>
) : BooksCache {

    override suspend fun getBook(bookId: String): BookEntity {
        return booksDatabase.cachedBooksDao().getBook(bookId).let {
            mapper.mapQueryResultToEntity(it)
        }
    }

    override suspend fun clearBooks() {
        booksDatabase.cachedBooksDao().deleteBooks()
    }

    override suspend fun saveToCache(books: List<BookEntity>) {
        val cachedBooks = books.map { mapper.mapEntityToModelsBundle(it) }
        cachedBooks.forEach { bundle ->
            booksDatabase.cachedBooksDao().insertAuthors(bundle.authors)
            booksDatabase.cachedBooksDao().insertBooks(asList(bundle.book))
            booksDatabase.cachedBooksDao().insertAuthorBookJoin(bundle.bookAuthorJoin)
        }
    }

    override suspend fun getBooks(): List<BookEntity> {
        return booksDatabase.cachedBooksDao().getBooks().map { cachedBook ->
            mapper.mapQueryResultToEntity(cachedBook)
        }
    }

    override suspend fun getLikedBooks(): List<BookEntity> {
        return booksDatabase.cachedBooksDao().getLikedBooks().map { cachedBook ->
            mapper.mapQueryResultToEntity(cachedBook)
        }
    }

    override suspend fun setBookLiked(bookId: String) {
        booksDatabase.cachedBooksDao().setLikedBook(true, bookId)
    }

    override suspend fun setBookUnliked(bookId: String) {
        booksDatabase.cachedBooksDao().setLikedBook(false, bookId)
    }

    override suspend fun areBooksCached(): Boolean {
        return booksDatabase.cachedBooksDao().getBooks().isNotEmpty()
    }

    override suspend fun setLastCacheTime(time: Long) {
        booksDatabase.configDao().insertConfig(Config(lastCacheTime = time))
    }

    @Suppress("UNNECESSARY_SAFE_CALL", "USELESS_ELVIS")
    override suspend fun isCacheExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 1440 * 1000).toLong() // 1440 minutes
        val lastCacheTime = booksDatabase.configDao().getConfig()?.lastCacheTime ?: 0L
        return currentTime - lastCacheTime > expirationTime
    }

}