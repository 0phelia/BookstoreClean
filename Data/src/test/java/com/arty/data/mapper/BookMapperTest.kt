package com.arty.data.mapper

import com.arty.data.model.BookAuthorEntity
import com.arty.data.model.BookEntity
import com.arty.data.model.BookRankingEntity
import com.arty.data.test.factory.BookFactory
import com.arty.domain.model.Book
import com.arty.domain.model.BookAuthor
import com.arty.domain.model.BookRanking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class BookMapperTest {

    @Mock private lateinit var authorMapper: BookAuthorMapper
    @Mock private lateinit var rankingMapper: BookRankingMapper
    private lateinit var mapper: BookMapper

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mapper = BookMapper(authorMapper, rankingMapper)
    }

    @Test
    fun mapFromEntityMapsData() {
        val entity = BookFactory.makeBookEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = BookFactory.makeBook()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BookEntity,
                                model: Book
    ) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.title, model.title)
        assertEquals(entity.description, model.description)
        assertEqualsAuthor(entity.authors, model.authors)
        assertEqualsRanking(entity.ranking, model.ranking)
        assertEquals(entity.coverImage, model.coverImage   )
        assertEquals(entity.pageCount, model.pageCount   )
        assertEquals(entity.datePublished, model.datePublished)
        assertEquals(entity.publisher, model.publisher)
        assertEquals(entity.isLiked, model.isLiked)
    }

    private fun assertEqualsRanking(entity: BookRankingEntity, model: BookRanking) {
        assertEquals(entity.rank, model.rank)
        assertEquals(entity.rankLastWeek, model.rankLastWeek)
        assertEquals(entity.weeksOnList, model.weeksOnList)
    }

    private fun assertEqualsAuthor(entity: List<BookAuthorEntity>, model: List<BookAuthor>) {
        // the rest is checked in AuthorMapperTest
        assertEquals(entity.size, model.size)
    }

}


