package com.arty.data.mapper

import com.arty.data.model.BookAuthorEntity
import com.arty.domain.model.BookAuthor
import javax.inject.Inject

open class BookAuthorMapper @Inject constructor(): EntityMapper<BookAuthorEntity, BookAuthor> {
    override fun mapToEntity(data: BookAuthor): BookAuthorEntity {
        return BookAuthorEntity(data.id, data.name)
    }

    override fun mapFromEntity(entity: BookAuthorEntity): BookAuthor {
        return BookAuthor(entity.id, entity.name)
    }
}