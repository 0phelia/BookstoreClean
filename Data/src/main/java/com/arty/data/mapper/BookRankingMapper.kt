package com.arty.data.mapper

import com.arty.data.model.BookRankingEntity
import com.arty.domain.model.BookRanking
import javax.inject.Inject

open class BookRankingMapper @Inject constructor(): EntityMapper<BookRankingEntity, BookRanking> {

    override fun mapToEntity(data: BookRanking): BookRankingEntity {
        return BookRankingEntity(data.rank, data.rankLastWeek, data.weeksOnList)
    }

    override fun mapFromEntity(entity: BookRankingEntity): BookRanking {
        return BookRanking(entity.rank, entity.rankLastWeek, entity.weeksOnList)
    }

}