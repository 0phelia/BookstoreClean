package com.arty.data.test.factory

import com.arty.data.model.BookEntity
import com.arty.data.test.factory.AuthorFactory.makeAuthorEntityList
import com.arty.data.test.factory.AuthorFactory.makeAuthorList
import com.arty.data.test.factory.RankingFactory.makeRanking
import com.arty.data.test.factory.RankingFactory.makeRankingEntity
import com.arty.domain.model.Book


object BookFactory {
    fun makeBookEntity(): BookEntity {
        return BookEntity(DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            makeAuthorEntityList(2), makeRankingEntity(),
            DataFactory.randomString(), DataFactory.randomInt(1000), DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomBoolean())
    }

    fun makeBook(): Book {
        return Book(
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            makeAuthorList(2),
            makeRanking(),
            DataFactory.randomString(),
            DataFactory.randomInt(1000),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomBoolean()
        )
    }


}