package com.arty.data.mapper

import com.arty.data.model.BookAuthorEntity
import com.arty.data.test.factory.AuthorFactory
import com.arty.domain.model.BookAuthor
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class AuthorMapperTest {
    private val mapper = BookAuthorMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = AuthorFactory.makeAuthorEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = AuthorFactory.makeAuthor()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BookAuthorEntity,
                                model: BookAuthor
    ) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
    }

}


