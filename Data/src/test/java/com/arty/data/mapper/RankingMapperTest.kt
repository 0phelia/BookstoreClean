package com.arty.data.mapper

import com.arty.data.model.BookRankingEntity
import com.arty.data.test.factory.RankingFactory
import com.arty.domain.model.BookRanking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class RankingMapperTest {
    private val mapper = BookRankingMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = RankingFactory.makeRankingEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = RankingFactory.makeRanking()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BookRankingEntity,
                                model: BookRanking
    ) {
        assertEquals(entity.rank, model.rank)
        assertEquals(entity.rankLastWeek, model.rankLastWeek)
        assertEquals(entity.weeksOnList, model.weeksOnList)
    }

}


