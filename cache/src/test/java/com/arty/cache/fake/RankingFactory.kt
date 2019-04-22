package com.arty.cache.fake

import com.arty.data.model.BookRankingEntity
import com.arty.domain.model.BookRanking

object RankingFactory {

    fun makeRankingEntity(): BookRankingEntity {
        return BookRankingEntity(DataFactory.randomInt(), DataFactory.randomInt(100), DataFactory.randomInt(100))
    }


    fun makeRanking(): BookRanking {
        return BookRanking(DataFactory.randomInt(), DataFactory.randomInt(100), DataFactory.randomInt(100))
    }

}