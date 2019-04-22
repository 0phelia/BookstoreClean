package com.arty.data.test.factory

import com.arty.data.model.BookRankingEntity
import com.arty.domain.model.BookRanking

object RankingFactory {

    fun makeRankingEntity(): BookRankingEntity {
        return BookRankingEntity(DataFactory.randomInt(5), DataFactory.randomInt(100), DataFactory.randomInt(100))
    }


    fun makeRanking(): BookRanking {
        return BookRanking(DataFactory.randomInt(5), DataFactory.randomInt(100), DataFactory.randomInt(100))
    }

}