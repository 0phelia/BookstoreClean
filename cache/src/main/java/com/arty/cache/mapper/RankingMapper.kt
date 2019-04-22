package com.arty.cache.mapper

import com.arty.cache.model.BookQueryResult
import com.arty.cache.model.CachedRanking
import com.arty.data.model.BookRankingEntity

class RankingMapper : Mapper<BookQueryResult, BookRankingEntity, CachedRanking> {

    override fun mapQueryResultToEntity(type: BookQueryResult): BookRankingEntity {
        return BookRankingEntity(type.rank, type.rankLastWeek, type.weeksOnList)
    }

    override fun mapEntityToModelsBundle(type: BookRankingEntity): CachedRanking {
        return CachedRanking(type.rank, type.rankLastWeek, type.weeksOnList)
    }

}
