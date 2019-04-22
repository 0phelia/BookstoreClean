package com.arty.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.arty.cache.BooksCacheImpl
import com.arty.cache.db.BookDatabase
import com.arty.cache.mapper.AuthorMapper
import com.arty.cache.mapper.BookMapper
import com.arty.cache.mapper.RankingMapper
import com.arty.data.repository.BooksCache
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun provideDatabase(context: Context): BookDatabase {
       return  Room.databaseBuilder(context,
           BookDatabase::class.java, "books.db")
           .build()
    }

    @Provides
    fun provideAuthorMapper(): AuthorMapper {
        return AuthorMapper()
    }

    @Provides
    fun provideRankingMapper(): RankingMapper {
        return RankingMapper()
    }

    @Provides
    fun provideBookMapper(authorMapper: AuthorMapper, rankingMapper: RankingMapper): BookMapper {
        return BookMapper(authorMapper, rankingMapper)
    }

    @Provides
    fun provideBookCache(db: BookDatabase, mapper: BookMapper): BooksCache {
        return BooksCacheImpl(db, mapper)
    }
}