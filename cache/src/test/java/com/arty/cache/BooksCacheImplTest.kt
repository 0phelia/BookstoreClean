package com.arty.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.arty.cache.db.BookDatabase
import com.arty.cache.fake.BookDataFactory
import com.arty.cache.mapper.AuthorMapper
import com.arty.cache.mapper.BookMapper
import com.arty.cache.mapper.RankingMapper
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class BooksCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val db = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application.applicationContext, BookDatabase::class.java)
        .allowMainThreadQueries()
        .build()
    private val authorMapper = AuthorMapper()
    private val rankingMapper = RankingMapper()
    private val mainMapper = BookMapper(authorMapper, rankingMapper)
    private val cache = BooksCacheImpl(db, mainMapper)

    @Test
    fun getBooksReturnsData() {
        runBlocking {
            val expected = listOf(BookDataFactory.makeBookEntity(3))
            cache.saveToCache(expected)
            val result = cache.getBooks()
            assertEquals(expected, result)
        }
    }

    @Test
    fun getLikedBooksReturnsData() {
        runBlocking {
            val likedBook = BookDataFactory.makeLikedBookEntity(1)
            val likedAndNotLikedBooks = listOf(likedBook, BookDataFactory.makeNotLikedBookEntity(1))
            cache.saveToCache(likedAndNotLikedBooks)

            val result = cache.getLikedBooks()
            assertEquals(1, result.size)
            assertEquals(likedBook, result[0])
        }
    }

    @Test
    fun setIsLikeWorks() {
        runBlocking {
            val notLikedBook = BookDataFactory.makeNotLikedBookEntity(1)
            cache.saveToCache(listOf(notLikedBook))

            cache.setBookLiked(notLikedBook.id)
            val result = cache.getLikedBooks()
            assertEquals(1, result.size)
            assertEquals(notLikedBook.id, result[0].id)
        }
    }

    @Test
    fun areBooksCachedReturnsStatus() {
        runBlocking {
            val books = listOf(BookDataFactory.makeBookEntity(1))
            cache.saveToCache(books)
            val result = cache.areBooksCached()
            assertTrue(result)
        }
    }

    @Test
    fun isBooksCacheExpiredReturnNotExpired() {
        runBlocking {
            cache.setLastCacheTime(System.currentTimeMillis() - 60 * 1000 * 9L) // 9 minutes elapsed
            val result = cache.isCacheExpired()
            assertFalse(result)
        }
    }

    @Test
    fun isBooksCacheExpiredReturnExpired1() {
        runBlocking {
            cache.setLastCacheTime(1000L)
            val result = cache.isCacheExpired()
            assertTrue(result)
        }
    }

    @Test
    fun isBooksCacheExpiredReturnExpired2() {
        runBlocking {
            cache.setLastCacheTime(System.currentTimeMillis() - 60 * 1441 * 1000L) // 1441 minutes elapsed
            val result = cache.isCacheExpired()
            assertTrue(result)
        }
    }
}