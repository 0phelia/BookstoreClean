package com.arty.data.mapper

import com.arty.data.model.BookEntity
import com.arty.domain.model.Book
import javax.inject.Inject

open class BookMapper @Inject constructor(
    private val authorMapper: BookAuthorMapper,
    private val rankingMapper: BookRankingMapper
): EntityMapper<BookEntity, Book> {
    override fun mapToEntity(data: Book): BookEntity {
        return BookEntity(data.id, data.title, data.description,
            data.authors.map { authorMapper.mapToEntity(it) },
            rankingMapper.mapToEntity(data.ranking),
            data.coverImage,
            data.pageCount,
            data.datePublished,  data.publisher, data.isLiked)
    }

    override fun mapFromEntity(entity: BookEntity): Book {
        return Book(entity.id, entity.title, entity.description,
            entity.authors.map { authorMapper.mapFromEntity(it) },
            rankingMapper.mapFromEntity(entity.ranking),
            entity.coverImage,
            entity.pageCount,
            entity.datePublished, entity.publisher, entity.isLiked)
    }
}