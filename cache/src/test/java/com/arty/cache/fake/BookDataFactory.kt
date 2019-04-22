package com.arty.cache.fake

import com.arty.cache.model.CachedBook
import com.arty.cache.model.CachedRanking
import com.arty.data.model.BookEntity

object BookDataFactory {

    fun makeBook(isLiked: Boolean): CachedBook {
        return CachedBook(
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            makeRanking(), DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            isLiked)
    }

    private fun makeRanking(): CachedRanking {
        return CachedRanking(DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt())
    }

    fun makeBookEntity(authorsNum: Int): BookEntity {
        return BookEntity(
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            AuthorFactory.makeAuthorEntityList(authorsNum),
            RankingFactory.makeRankingEntity(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomBoolean())
    }

    fun makeLikedBookEntity(authorsNum: Int): BookEntity {
        return makeBookEntity(authorsNum).copy(isLiked = true)
    }

    fun makeNotLikedBookEntity(authorsNum: Int): BookEntity {
        return makeBookEntity(authorsNum).copy(isLiked = false)
    }

}