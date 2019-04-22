package com.arty.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arty.cache.dao.CachedBooksDao
import com.arty.cache.dao.ConfigDao
import com.arty.cache.model.BookAuthorJoin
import com.arty.cache.model.CachedAuthor
import com.arty.cache.model.CachedBook
import com.arty.cache.model.Config
import javax.inject.Inject

@Database(entities = [CachedBook::class, CachedAuthor::class, BookAuthorJoin::class, Config::class], version = 1, exportSchema = false)
abstract class BookDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedBooksDao(): CachedBooksDao

    abstract fun configDao(): ConfigDao

}